package com.example.iphone_web_be.modules.products.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageRequest {

    /**
     * ID của biến thể (ProductVariant) - Có thể null nếu là ảnh chung của sản phẩm.
     */
    String variantId;

    @NotBlank(message = "Đường dẫn hình ảnh không được để trống")
    String imageUrl;

    String altText;

    Integer displayOrder;

    Boolean primary;
}
