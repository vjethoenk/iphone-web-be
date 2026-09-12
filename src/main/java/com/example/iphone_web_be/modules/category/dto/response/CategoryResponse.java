package com.example.iphone_web_be.modules.category.dto.response;

import com.example.iphone_web_be.common.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    String id;
    String name;
    String slug;
    String description;
    String imageUrl;
    ProductStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
