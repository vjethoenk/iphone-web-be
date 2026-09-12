package com.example.iphone_web_be.modules.products.repository;

import com.example.iphone_web_be.modules.products.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, String> {
    Optional<Storage> findByName(String name);
}
