package com.example.iphone_web_be.modules.products.dto.response;

import com.example.iphone_web_be.common.enums.ProductStatus;
import com.example.iphone_web_be.modules.category.dto.response.CategoryResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBaseResponse {
    String id;
    String category;
    String name;
    String slug;
    String brand;
    String shortDescription;
    String description;
    String thumbnail;
    ProductStatus status;
    boolean featured;
    BigDecimal price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
