package com.example.iphone_web_be.modules.products.dto.request;

import com.example.iphone_web_be.common.enums.ProductStatus;
import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {

    String categoryId;

    String name;

    String slug;

    String brand;

    String shortDescription;

    String description;

    String thumbnail;

    ProductStatus status;

    Boolean featured;

    @Valid
    ProductSpecificationRequest specification;

    @Valid
    List<ProductVariantRequest> variants;

    @Valid
    List<ProductImageRequest> images;
}