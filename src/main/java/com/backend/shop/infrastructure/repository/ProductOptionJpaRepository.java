package com.backend.shop.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.shop.infrastructure.entity.ProductOptionEntity;

public interface ProductOptionJpaRepository extends JpaRepository<ProductOptionEntity,Long> {
    
}
