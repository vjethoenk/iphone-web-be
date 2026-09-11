package com.example.iphone_web_be.modules.permission.mapper;

import com.example.iphone_web_be.modules.permission.dto.request.PermissionRequest;
import com.example.iphone_web_be.modules.permission.dto.response.PermissionResponse;
import com.example.iphone_web_be.modules.permission.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
