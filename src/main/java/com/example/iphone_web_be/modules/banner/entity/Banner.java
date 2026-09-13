package com.example.iphone_web_be.modules.banner.entity;

import com.example.iphone_web_be.common.entity.BaseEntity;
import com.example.iphone_web_be.modules.banner.enums.BannerStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "banners",
        indexes = {
                @Index(name = "idx_banner_status", columnList = "status"),
                @Index(name = "idx_banner_position", columnList = "position")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banner extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "image_url", nullable = false, length = 1000)
    private String imageUrl;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private BannerStatus status;
}
