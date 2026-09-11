package com.example.iphone_web_be.modules.otp.modal;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpData {

    private String otp;

    private String senderAccount;

    private String receiverAccount;

    private BigDecimal amount;

    private String description;

    private LocalDateTime expiredAt;
}
