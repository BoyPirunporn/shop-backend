package com.backend.shop.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.shop.infrastructure.entity.ProductEntity;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity,Long>{
    Optional<ProductEntity> findByNameContainingIgnoreCase(String name);
}
