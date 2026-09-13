package com.example.iphone_web_be.modules.banner.controller;

import com.example.iphone_web_be.common.dto.ApiResponse;
import com.example.iphone_web_be.modules.banner.dto.request.BannerRequest;
import com.example.iphone_web_be.modules.banner.dto.response.BannerResponse;
import com.example.iphone_web_be.modules.banner.enums.BannerStatus;
import com.example.iphone_web_be.modules.banner.service.BannerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banners")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BannerController {

    BannerService bannerService;

    @PostMapping
    public ApiResponse<BannerResponse> create(
            @Valid @RequestBody BannerRequest request
    ) {

        return ApiResponse.<BannerResponse>builder()
                .result(bannerService.create(request)).build();
    }

    @GetMapping
    public ApiResponse<List<BannerResponse>> getAll() {

        return ApiResponse.<List<BannerResponse>>builder()
                .result(bannerService.getAll()).build();
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<BannerResponse>> getByStatus(
            @PathVariable BannerStatus status
    ) {

        return ApiResponse.<List<BannerResponse>>builder()
                .result(bannerService.getByStatus(status)).build();
    }

}