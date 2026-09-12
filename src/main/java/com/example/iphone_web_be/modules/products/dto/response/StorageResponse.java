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
public class StorageResponse {
    String id;
    String name;
    int capacityGb;
    boolean active;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
