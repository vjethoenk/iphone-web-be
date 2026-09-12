package com.example.iphone_web_be.modules.products.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSpecificationResponse {
    String id;
    String displaySize;
    String displayType;
    String displayResolution;
    String refreshRate;
    String displayBrightness;

    String processor;
    String cpu;
    String gpu;

    String mainCamera;
    String ultraWideCamera;
    String telephotoCamera;
    String frontCamera;
    String videoRecording;

    String batteryCapacity;
    String charging;
    boolean wirelessCharging;

    String wifi;
    String bluetooth;
    String cellular;

    String height;
    String width;
    String thickness;
    String weight;

    String operatingSystem;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
