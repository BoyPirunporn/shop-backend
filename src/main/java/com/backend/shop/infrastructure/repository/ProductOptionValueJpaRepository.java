package com.backend.shop.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.shop.infrastructure.entity.ProductOptionValueEntity;

public interface ProductOptionValueJpaRepository extends JpaRepository<ProductOptionValueEntity,Long>{
    
}
