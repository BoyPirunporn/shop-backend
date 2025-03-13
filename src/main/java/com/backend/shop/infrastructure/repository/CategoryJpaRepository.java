package com.backend.shop.infrastructure.repository;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.shop.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity,Long> {
//    @Query("SELECT c FROM CategoryEntity c WHERE c.name LIKE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Optional<CategoryEntity> findByNameContainingIgnoreCase(@Param("name") String name);
}
