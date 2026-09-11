package com.example.iphone_web_be.modules.products.entity;

import com.example.iphone_web_be.common.entity.BaseEntity;
import com.example.iphone_web_be.common.enums.ProductStatus;
import com.example.iphone_web_be.modules.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "products",
        indexes = {
                @Index(name = "idx_product_category", columnList = "category_id"),
                @Index(name = "idx_product_status", columnList = "status"),
                @Index(name = "idx_product_brand", columnList = "brand"),
                @Index(name = "idx_product_featured", columnList = "is_featured")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Column(nullable = false, length = 255)
    String name;

    @Column(nullable = false, unique = true, length = 300)
    String slug;

    @Builder.Default
    @Column(nullable = false, length = 100)
    String brand = "Apple";

    @Column(name = "short_description", length = 500)
    String shortDescription;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(length = 500)
    String thumbnail;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    ProductStatus status = ProductStatus.DRAFT;

    @Builder.Default
    @Column(name = "is_featured", nullable = false)
    boolean featured = false;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}
