package com.example.iphone_web_be.modules.products.controller;

import com.example.iphone_web_be.common.dto.ApiResponse;
import com.example.iphone_web_be.modules.products.dto.request.ColorRequest;
import com.example.iphone_web_be.modules.products.dto.response.ColorResponse;
import com.example.iphone_web_be.modules.products.service.ColorService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorController {
    ColorService colorService;

    @PostMapping
    ApiResponse<ColorResponse> create (@RequestBody @Valid ColorRequest request){
        return ApiResponse.<ColorResponse>builder()
                .result(colorService.createColor(request)).build();
    }

    @GetMapping
    ApiResponse<List<ColorResponse>> all(){
        return ApiResponse.<List<ColorResponse>>builder()
                .result(colorService.getAll()).build();
    }

}
