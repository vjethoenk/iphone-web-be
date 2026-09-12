package com.example.iphone_web_be.modules.products.dto.request;

import com.example.iphone_web_be.common.enums.ProductStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {

    @NotBlank(message = "Danh mục không được để trống")
    String categoryId;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    String name;

    String slug;

    @Builder.Default
    String brand = "Apple";

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
