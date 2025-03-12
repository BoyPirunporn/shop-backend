package com.backend.shop.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.shop.infrastructure.entity.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity,Long>{
    
}
