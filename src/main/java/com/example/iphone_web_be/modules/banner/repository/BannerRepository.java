package com.example.iphone_web_be.modules.banner.repository;

import com.example.iphone_web_be.modules.banner.entity.Banner;
import com.example.iphone_web_be.modules.banner.enums.BannerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BannerRepository extends JpaRepository<Banner, UUID> {

    List<Banner> findByStatusOrderByDisplayOrderAsc(
            BannerStatus status
    );

    List<Banner> findAllByOrderByDisplayOrderAsc();
}
