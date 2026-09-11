package com.example.iphone_web_be.modules.role.mapper;

import com.example.iphone_web_be.modules.role.dto.request.RoleRequest;
import com.example.iphone_web_be.modules.role.dto.response.RoleResponse;
import com.example.iphone_web_be.modules.role.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "name", ignore = true)
    void updateRole(@MappingTarget Role role , RoleRequest request);
}
