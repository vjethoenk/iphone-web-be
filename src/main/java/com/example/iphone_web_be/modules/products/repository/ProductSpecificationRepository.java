package com.example.iphone_web_be.modules.products.repository;

import com.example.iphone_web_be.modules.products.entity.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, String> {
    Optional<ProductSpecification> findByProductId(String productId);
}
