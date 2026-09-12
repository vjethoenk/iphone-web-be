package com.example.iphone_web_be.modules.products.mapper;

import com.example.iphone_web_be.modules.products.dto.request.ColorRequest;
import com.example.iphone_web_be.modules.products.dto.response.ColorResponse;
import com.example.iphone_web_be.modules.products.entity.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    Color toColor(ColorRequest request);

    ColorResponse toColorResponse(Color color);
}
