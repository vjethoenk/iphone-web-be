package com.example.iphone_web_be.modules.products.entity;

import com.example.iphone_web_be.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "product_variants",
        indexes = {
                @Index(name = "idx_variant_product", columnList = "product_id"),
                @Index(name = "idx_variant_color", columnList = "color_id"),
                @Index(name = "idx_variant_storage", columnList = "storage_id"),
                @Index(name = "idx_variant_stock", columnList = "stock_quantity")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_variant_product_color_storage",
                        columnNames = {"product_id", "color_id", "storage_id"}
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariant extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", nullable = false)
    Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id", nullable = false)
    Storage storage;

    @Column(nullable = false, unique = true, length = 100)
    String sku;

    @Column(nullable = false, precision = 15, scale = 2)
    BigDecimal price;

    @Column(name = "original_price", precision = 15, scale = 2)
    BigDecimal originalPrice;

    @Builder.Default
    @Column(name = "stock_quantity", nullable = false)
    int stockQuantity = 0;

    @Builder.Default
    @Column(name = "low_stock_threshold", nullable = false)
    int lowStockThreshold = 5;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    boolean active = true;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}