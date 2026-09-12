package com.example.iphone_web_be.modules.products.controller;

import com.example.iphone_web_be.common.dto.ApiResponse;
import com.example.iphone_web_be.modules.products.dto.request.StorageRequest;
import com.example.iphone_web_be.modules.products.dto.response.StorageResponse;

import com.example.iphone_web_be.modules.products.service.StorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StorageController {
    StorageService storageService;

    @PostMapping
    ApiResponse<StorageResponse> create(@RequestBody StorageRequest request){
        return ApiResponse.<StorageResponse>builder()
                .result(storageService.createStorage(request)).build();
    }

    @GetMapping
    ApiResponse<List<StorageResponse>> getAll (){
        return ApiResponse.<List<StorageResponse>>builder()
                .result(storageService.getAll()).build();
    }
}
