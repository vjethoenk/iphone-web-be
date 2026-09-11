package com.example.iphone_web_be.modules.user.dto.request;

import com.example.iphone_web_be.modules.user.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String username;
    String email;
    String phone;
    String address;
    String citizenId;
    LocalDate dateOfBirth;
    String avatar;
    Gender gender;
}
