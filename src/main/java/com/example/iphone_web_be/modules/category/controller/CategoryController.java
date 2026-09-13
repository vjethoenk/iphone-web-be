package com.example.iphone_web_be.modules.category.controller;

import com.example.iphone_web_be.common.dto.ApiResponse;
import com.example.iphone_web_be.modules.category.dto.request.CategoryRequest;
import com.example.iphone_web_be.modules.category.dto.response.CategoryResponse;
import com.example.iphone_web_be.modules.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryResponse> create (@RequestBody @Valid CategoryRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request)).build();
    }

    @GetMapping("/{slug}")
    ApiResponse<CategoryResponse> getBySlug(@PathVariable String slug){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getBySlug(slug)).build();
    }

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAll(){
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAll()).build();
    }

    @PutMapping("/{categoryId}")
    ApiResponse<CategoryResponse> getByID(@PathVariable String categoryId, @RequestBody CategoryRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getById(categoryId, request)).build();
    }
}
