package com.example.iphone_web_be.modules.auth.repository;

import com.example.iphone_web_be.modules.auth.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
