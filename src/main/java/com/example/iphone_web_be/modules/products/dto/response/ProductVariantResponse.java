package com.example.iphone_web_be.modules.products.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantResponse {
    String id;
    ColorResponse color;
    StorageResponse storage;
    String sku;
    BigDecimal price;
    BigDecimal originalPrice;
    int stockQuantity;
    int lowStockThreshold;
    boolean active;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
