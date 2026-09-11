package com.example.iphone_web_be.modules.user.dto.request;

import com.example.iphone_web_be.modules.role.entity.Role;
import com.example.iphone_web_be.modules.user.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

    @NotBlank(message = "USERNAME_REQUIRED")
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Email(message = "EMAIL_INVALID")
    @Size(max = 100)
    String email;

    @Pattern(
            regexp = "^0\\d{9}$",
            message = "PHONE_INVALID"
    )
    String phone;

    String customerCode;

//    @NotBlank(message = "CITIZEN_ID_REQUIRED")
//    @Pattern(
//            regexp = "^\\d{9}|\\d{12}$",
//            message = "CITIZEN_ID_INVALID"
//    )
//    String citizenId;
//
//    LocalDate dateOfBirth;
//
//    Gender gender;
//
//    String address;
//
//    String avatar;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
}
