package com.example.iphone_web_be.redis;

public final class RedisKeys {

    private RedisKeys() {
    }

    public static String transferOtp(String transactionId) {
        return "transfer:otp:" + transactionId;
    }
}
