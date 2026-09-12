package com.example.iphone_web_be.modules.products.controller;

import com.example.iphone_web_be.common.dto.ApiResponse;
import com.example.iphone_web_be.modules.products.dto.request.ProductCreationRequest;
import com.example.iphone_web_be.modules.products.dto.response.ProductBaseResponse;
import com.example.iphone_web_be.modules.products.dto.response.ProductDetailResponse;
import com.example.iphone_web_be.modules.products.dto.response.ProductResponse;
import com.example.iphone_web_be.modules.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    ProductService productService;

    @PostMapping
    ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(request))
                .build();
    }

    @GetMapping("/{slug}")
    ApiResponse<ProductDetailResponse> getProductDetail(@PathVariable String slug) {
        return ApiResponse.<com.example.iphone_web_be.modules.products.dto.response.ProductDetailResponse>builder()
                .result(productService.getProductDetail(slug))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProductBaseResponse>> getAll(){
        return ApiResponse.<List<ProductBaseResponse>>builder().result(productService.getAll()).build();
    }
}
