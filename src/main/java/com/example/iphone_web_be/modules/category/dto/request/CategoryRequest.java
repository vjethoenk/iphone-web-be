package com.example.iphone_web_be.modules.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = 100, message = "Tên danh mục không được vượt quá 100 ký tự")
    String name;

    @Size(max = 150, message = "Slug không được vượt quá 150 ký tự")
    String slug;

    @Size(max = 500, message = "Mô tả không được vượt quá 500 ký tự")
    String description;

    @Size(max = 500, message = "URL hình ảnh không được vượt quá 500 ký tự")
    @Pattern(
            regexp = "^(https?://).+",
            message = "URL hình ảnh không hợp lệ"
    )
    String imageUrl;
}