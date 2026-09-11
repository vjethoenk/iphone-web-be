package com.example.iphone_web_be.modules.otp.service;

import com.example.iphone_web_be.exception.AppException;
import com.example.iphone_web_be.exception.ErrorCode;
import com.example.iphone_web_be.modules.email.service.EmailService;

import com.example.iphone_web_be.modules.otp.modal.TransferOtpCache;
import com.example.iphone_web_be.redis.RedisKeys;
import com.example.iphone_web_be.redis.RedisService;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpService {

    RedisService redisService;
    EmailService emailService;
    PasswordEncoder passwordEncoder;

    static long OTP_TTL_MINUTES = 5;

    public void sendTransferOtp(String transactionId,
                                String userId,
                                String email) {

        String otp = generateOtp();

        TransferOtpCache cache = TransferOtpCache.builder()
                .transactionId(transactionId)
                .userId(userId)
                .email(email)
                .otpHash(passwordEncoder.encode(otp))
                .attempt(0)
                .build();

        redisService.save(
                RedisKeys.transferOtp(transactionId),
                cache,
                OTP_TTL_MINUTES,
                TimeUnit.MINUTES
        );

        try {
            emailService.sendTransferOtp(email, otp);
        } catch (MessagingException e) {
            log.error("Failed to send OTP email for transactionId: {}", transactionId, e);
            throw new AppException(ErrorCode.OTP_SEND_FAILED);
        }
    }


    public boolean verifyTransferOtp(String transactionId,
                                     String otp) {

        String key = RedisKeys.transferOtp(transactionId);

        TransferOtpCache cache =
                (TransferOtpCache) redisService.get(key);

        if (cache == null) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }

        if (!passwordEncoder.matches(otp, cache.getOtpHash())) {

            cache.setAttempt(cache.getAttempt() + 1);

            redisService.save(
                    key,
                    cache,
                    OTP_TTL_MINUTES,
                    TimeUnit.MINUTES
            );

            if (cache.getAttempt() >= 5) {
                redisService.delete(key);
                throw new AppException(ErrorCode.OTP_MAX_ATTEMPT);
            }

            throw new AppException(ErrorCode.INVALID_OTP);
        }

        redisService.delete(key);

        return true;
    }

    private String generateOtp() {

        SecureRandom random = new SecureRandom();

        return String.valueOf(
                100000 + random.nextInt(900000)
        );
    }
}
