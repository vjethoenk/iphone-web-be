package com.example.iphone_web_be.modules.banner.mapper;

import com.example.iphone_web_be.modules.banner.dto.request.BannerRequest;
import com.example.iphone_web_be.modules.banner.dto.response.BannerResponse;
import com.example.iphone_web_be.modules.banner.entity.Banner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BannerMapper {
    Banner toBanner(BannerRequest request);
    BannerResponse toBannerResponse(Banner banner);
}
