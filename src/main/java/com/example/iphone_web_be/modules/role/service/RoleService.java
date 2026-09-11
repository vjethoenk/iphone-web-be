package com.example.iphone_web_be.modules.role.service;

import com.example.iphone_web_be.exception.AppException;
import com.example.iphone_web_be.exception.ErrorCode;
import com.example.iphone_web_be.modules.permission.repository.PermissionRepository;
import com.example.iphone_web_be.modules.role.dto.request.RoleRequest;
import com.example.iphone_web_be.modules.role.dto.response.RoleResponse;
import com.example.iphone_web_be.modules.role.entity.Role;
import com.example.iphone_web_be.modules.role.mapper.RoleMapper;
import com.example.iphone_web_be.modules.role.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse createRole(RoleRequest request){
        var roles = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());

        roles.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(roles));
    }

    public List<RoleResponse> getAll(){
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    public RoleResponse updateRole(String roleId, RoleRequest request){
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new AppException(ErrorCode.ROLE_EXISTED));

        roleMapper.updateRole(role, request);

        var permission = permissionRepository.findAllById(request.getPermissions());

        role.getPermissions().addAll(permission);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }
}
