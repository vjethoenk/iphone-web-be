package com.example.iphone_web_be.modules.products.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ColorRequest {

    @NotBlank(message = "Color name is required")
    @Size(max = 100, message = "Color name must not exceed 100 characters")
    String name;

    @NotBlank(message = "Hex code is required")
    @Pattern(
            regexp = "^#[0-9A-Fa-f]{6}$",
            message = "Hex code must be in format #RRGGBB"
    )
    String hexCode;

    @Min(value = 0, message = "Display order must be greater than or equal to 0")
    int displayOrder;

    boolean active;
}
