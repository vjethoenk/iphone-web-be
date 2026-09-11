package com.example.iphone_web_be.modules.otp.modal;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferOtpCache {

    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    private String email;

    private String otpHash;

    private int attempt;
}
