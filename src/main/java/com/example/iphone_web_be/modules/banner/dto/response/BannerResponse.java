package com.example.iphone_web_be.modules.banner.dto.response;


import com.example.iphone_web_be.modules.banner.enums.BannerStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BannerResponse {

     UUID id;

     String title;

     String imageUrl;

     Integer displayOrder;

     BannerStatus status;

     LocalDateTime createdAt;

     LocalDateTime updatedAt;
}