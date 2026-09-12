package com.example.iphone_web_be.modules.products.entity;

import com.example.iphone_web_be.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(
        name = "product_images",
        indexes = {
                @Index(name = "idx_product_image_product", columnList = "product_id"),
                @Index(name = "idx_product_image_variant", columnList = "variant_id"),
                @Index(name = "idx_product_image_color", columnList = "color_id")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImage extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    /**
     * Có thể null nếu ảnh dùng chung cho toàn bộ Product.
     *
     * Ví dụ:
     * - Ảnh mặt trước dùng chung → variant = null
     * - Ảnh iPhone màu Black → variant = variant Black
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
    ProductVariant variant;

    @Column(name = "image_url", nullable = false, length = 500)
    String imageUrl;

    @Column(name = "alt_text", length = 255)
    String altText;

    @Builder.Default
    @Column(name = "display_order", nullable = false)
    int displayOrder = 0;

    @Builder.Default
    @Column(name = "is_primary", nullable = false)
    boolean primary = false;
}