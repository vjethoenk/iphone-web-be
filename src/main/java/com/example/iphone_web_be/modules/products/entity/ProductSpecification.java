package com.example.iphone_web_be.modules.products.entity;

import com.example.iphone_web_be.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Entity
@Table(
        name = "product_specifications",
        indexes = {
                @Index(name = "idx_specification_product", columnList = "product_id")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSpecification extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            unique = true
    )
    Product product;

    // =========================
    // DISPLAY
    // =========================

    @Column(name = "display_size", length = 50)
    String displaySize;

    @Column(name = "display_type", length = 100)
    String displayType;

    @Column(name = "display_resolution", length = 100)
    String displayResolution;

    @Column(name = "refresh_rate", length = 50)
    String refreshRate;

    @Column(name = "display_brightness", length = 50)
    String displayBrightness;

    // =========================
    // PROCESSOR
    // =========================

    @Column(name = "processor", length = 100)
    String processor;

    @Column(name = "cpu", length = 100)
    String cpu;

    @Column(name = "gpu", length = 100)
    String gpu;

    // =========================
    // CAMERA
    // =========================

    @Column(name = "main_camera", length = 100)
    String mainCamera;

    @Column(name = "ultra_wide_camera", length = 100)
    String ultraWideCamera;

    @Column(name = "telephoto_camera", length = 100)
    String telephotoCamera;

    @Column(name = "front_camera", length = 100)
    String frontCamera;

    @Column(name = "video_recording", length = 255)
    String videoRecording;

    // =========================
    // BATTERY
    // =========================

    @Column(name = "battery_capacity", length = 50)
    String batteryCapacity;

    @Column(name = "charging", length = 100)
    String charging;

    @Builder.Default
    @Column(name = "wireless_charging", nullable = false)
    boolean wirelessCharging = false;

    // =========================
    // CONNECTIVITY
    // =========================

    @Column(name = "wifi", length = 100)
    String wifi;

    @Column(name = "bluetooth", length = 100)
    String bluetooth;

    @Column(name = "cellular", length = 100)
    String cellular;

    // =========================
    // DIMENSIONS
    // =========================

    @Column(length = 50)
    String height;

    @Column(length = 50)
    String width;

    @Column(length = 50)
    String thickness;

    @Column(length = 50)
    String weight;

    // =========================
    // SOFTWARE
    // =========================

    @Column(name = "operating_system", length = 100)
    String operatingSystem;
}
