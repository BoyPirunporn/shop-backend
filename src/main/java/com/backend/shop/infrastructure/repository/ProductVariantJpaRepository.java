package com.backend.shop.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.shop.infrastructure.entity.ProductVariantEntity;

public interface ProductVariantJpaRepository  extends JpaRepository<ProductVariantEntity,Long>{
    
}
