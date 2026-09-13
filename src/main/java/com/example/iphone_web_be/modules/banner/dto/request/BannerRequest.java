package com.example.iphone_web_be.modules.banner.dto.request;

import com.example.iphone_web_be.modules.banner.enums.BannerStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BannerRequest {

    @NotBlank(message = "Tiêu đề banner không được để trống")
    @Size(
            max = 255,
            message = "Tiêu đề banner không được vượt quá 255 ký tự"
    )
    String title;

    @NotBlank(message = "Image URL không được để trống")
    @Size(
            max = 1000,
            message = "Image URL không được vượt quá 1000 ký tự"
    )
    String imageUrl;

    @NotNull(message = "Thứ tự hiển thị không được để trống")
    @Min(value = 0, message = "Thứ tự hiển thị phải lớn hơn hoặc bằng 0")
    Integer displayOrder;

    @NotNull(message = "Trạng thái không được để trống")
    BannerStatus status;
}
