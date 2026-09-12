package com.example.iphone_web_be.modules.products.repository;

import com.example.iphone_web_be.modules.products.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, String> {
    Optional<Color> findByName(String name);
}
