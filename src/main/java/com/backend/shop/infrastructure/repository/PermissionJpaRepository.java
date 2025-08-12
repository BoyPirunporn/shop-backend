package com.backend.shop.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.shop.infrastructure.entity.PermissionEntity;

public interface PermissionJpaRepository extends JpaRepository<PermissionEntity,Long>{
    
}
