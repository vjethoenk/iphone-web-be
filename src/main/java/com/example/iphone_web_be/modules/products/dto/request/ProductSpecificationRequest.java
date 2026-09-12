package com.example.iphone_web_be.modules.products.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSpecificationRequest {
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
    Boolean wirelessCharging;

    String wifi;
    String bluetooth;
    String cellular;

    String height;
    String width;
    String thickness;
    String weight;

    String operatingSystem;
}
