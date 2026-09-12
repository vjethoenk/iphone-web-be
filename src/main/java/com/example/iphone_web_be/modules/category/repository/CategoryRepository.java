package com.example.iphone_web_be.modules.category.repository;

import com.example.iphone_web_be.modules.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsBySlug(String slug);
    boolean existsByName(String name);
    Optional<Category> findBySlug(String slug);
}
