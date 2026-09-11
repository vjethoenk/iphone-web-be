package com.example.iphone_web_be.modules.auth.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    String id;

    String username;

    String email;

    Set<String> roles;

    Set<String> permissions;
}
