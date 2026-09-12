package com.example.iphone_web_be.modules.products.service;

import com.example.iphone_web_be.exception.AppException;
import com.example.iphone_web_be.exception.ErrorCode;
import com.example.iphone_web_be.modules.products.dto.request.ColorRequest;
import com.example.iphone_web_be.modules.products.dto.response.ColorResponse;
import com.example.iphone_web_be.modules.products.entity.Color;
import com.example.iphone_web_be.modules.products.mapper.ColorMapper;
import com.example.iphone_web_be.modules.products.repository.ColorRepository;
import com.example.iphone_web_be.modules.user.dto.response.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorService {
    ColorRepository colorRepository;
    ColorMapper colorMapper;

    public ColorResponse createColor(ColorRequest request) {

        if (colorRepository.existsByName(request.getName().trim())) {
            throw new AppException(ErrorCode.COLOR_EXISTED);
        }

        Color color = colorMapper.toColor(request);

        return colorMapper.toColorResponse(
                colorRepository.save(color)
        );
    }

    public List<ColorResponse> getAll(){
        return colorRepository.findAll().stream().map(colorMapper::toColorResponse).toList();
    }
}
