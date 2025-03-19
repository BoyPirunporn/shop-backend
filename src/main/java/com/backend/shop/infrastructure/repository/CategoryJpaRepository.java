package com.backend.shop.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.backend.shop.infrastructure.entity.CategoryEntity;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity,Long> {
//    @Query("SELECT c FROM CategoryEntity c WHERE c.name LIKE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Optional<CategoryEntity> findByNameContainingIgnoreCase(@Param("name") String name);
    Optional<CategoryEntity> findFirstByParentId(Long id);
}
