package com.example.iphone_web_be.modules.products.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductColorGalleryResponse {
    String id;
    String name;
    String hexCode;
    List<ProductImageResponse> images;
}
