package com.example.iphone_web_be.modules.products.repository;

import com.example.iphone_web_be.modules.products.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, String> {
    boolean existsBySku(String sku);
    List<ProductVariant> findByProductId(String productId);
}
