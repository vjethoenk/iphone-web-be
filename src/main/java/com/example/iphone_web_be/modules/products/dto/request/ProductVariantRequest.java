package com.example.iphone_web_be.modules.products.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantRequest {

    @NotBlank(message = "Màu sắc không được để trống")
    String colorId;

    @NotBlank(message = "Dung lượng không được để trống")
    String storageId;

    @NotBlank(message = "Mã SKU không được để trống")
    String sku;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 0, message = "Giá sản phẩm phải lớn hơn hoặc bằng 0")
    BigDecimal price;

    BigDecimal originalPrice;

    @Min(value = 0, message = "Số lượng tồn kho phải lớn hơn hoặc bằng 0")
    Integer stockQuantity;

    Integer lowStockThreshold;

    Boolean active;
}
