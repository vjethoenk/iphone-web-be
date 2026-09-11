package com.example.iphone_web_be.modules.role.controller;

import com.example.iphone_web_be.modules.role.dto.request.RoleRequest;
import com.example.iphone_web_be.modules.role.dto.response.RoleResponse;
import com.example.iphone_web_be.modules.role.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import com.example.iphone_web_be.common.dto.ApiResponse;

import java.util.List;


@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createPermission(@RequestBody @Valid RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @PutMapping("/{roleId}")
    ApiResponse<RoleResponse> updateRole(@PathVariable String roleId, @RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.updateRole(roleId, request)).build();
    }
}
