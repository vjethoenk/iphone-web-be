package com.example.iphone_web_be.modules.products.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageResponse {
    String id;
    String variantId;
    String imageUrl;
    String altText;
    int displayOrder;
    boolean primary;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
