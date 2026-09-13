package com.example.iphone_web_be.modules.banner.service;

import com.example.iphone_web_be.modules.banner.dto.request.BannerRequest;
import com.example.iphone_web_be.modules.banner.dto.response.BannerResponse;
import com.example.iphone_web_be.modules.banner.entity.Banner;
import com.example.iphone_web_be.modules.banner.enums.BannerStatus;
import com.example.iphone_web_be.modules.banner.mapper.BannerMapper;
import com.example.iphone_web_be.modules.banner.repository.BannerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BannerService {
    BannerMapper bannerMapper;
    BannerRepository bannerRepository;

    public BannerResponse create(BannerRequest request) {

        Banner banner = bannerMapper.toBanner(request);

        Banner savedBanner = bannerRepository.save(banner);

        return bannerMapper.toBannerResponse(savedBanner);
    }

    public List<BannerResponse> getAll() {

        return bannerRepository
                .findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(bannerMapper::toBannerResponse)
                .toList();
    }

    public List<BannerResponse> getByStatus(
            BannerStatus status
    ) {

        return bannerRepository
                .findByStatusOrderByDisplayOrderAsc(status)
                .stream()
                .map(bannerMapper::toBannerResponse)
                .toList();
    }
}
