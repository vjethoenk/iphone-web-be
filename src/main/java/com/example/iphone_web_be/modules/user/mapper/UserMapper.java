package com.example.iphone_web_be.modules.user.mapper;

import com.example.iphone_web_be.modules.user.dto.request.UserCreateRequest;
import com.example.iphone_web_be.modules.user.dto.request.UserUpdateRequest;
import com.example.iphone_web_be.modules.user.dto.response.UserResponse;
import com.example.iphone_web_be.modules.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)

    void updateUser(@MappingTarget User user,
                    UserUpdateRequest request);
}
