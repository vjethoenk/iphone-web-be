package com.example.iphone_web_be.modules.user.dto.response;

import com.example.iphone_web_be.modules.role.dto.response.RoleResponse;
import com.example.iphone_web_be.modules.user.enums.Gender;
import com.example.iphone_web_be.modules.user.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    UUID id;
    String customerCode;
    String username;
    String email;
    String phone;
    String citizenId;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
    String avatar;
    UserStatus status;
    Set<RoleResponse> roles;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
