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
public class ColorResponse {
    String id;
    String name;
    String hexCode;
    int displayOrder;
    boolean active;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
