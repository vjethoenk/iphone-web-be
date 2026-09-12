package com.example.iphone_web_be.modules.products.dto.response;

import com.example.iphone_web_be.common.enums.ProductStatus;
import com.example.iphone_web_be.modules.category.dto.response.CategoryResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    CategoryResponse category;
    String name;
    String slug;
    String brand;
    String shortDescription;
    String description;
    String thumbnail;
    ProductStatus status;
    boolean featured;
    ProductSpecificationResponse specification;
    List<ProductVariantResponse> variants;
    List<ProductImageResponse> images;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
