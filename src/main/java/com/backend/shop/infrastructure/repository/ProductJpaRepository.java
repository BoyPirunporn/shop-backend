package com.backend.shop.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.backend.shop.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductJpaRepository
        extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    Optional<ProductEntity> findByNameContainingIgnoreCase(String name);


    @Query(
            value = """
                    WITH RECURSIVE category_hierarchy AS (
                        SELECT id, name, parent_id
                        FROM category
                        WHERE lower(name) = :category
                        UNION ALL
                        SELECT c.id, c.name, c.parent_id
                        FROM category c
                        INNER JOIN category_hierarchy ch ON ch.id = c.parent_id
                    )
                    SELECT p.*
                    FROM products p
                    JOIN category c ON p.category_id = c.id
                    JOIN category_hierarchy ch ON ch.id = c.id
                    """,
            countQuery = """
                    WITH RECURSIVE category_hierarchy AS (
                        SELECT id, name, parent_id
                        FROM category
                        WHERE lower(name) = :category  -- หมวดหมู่หลักที่ต้องการค้นหา
                        UNION ALL
                        SELECT c.id, c.name, c.parent_id
                        FROM category c
                        INNER JOIN category_hierarchy ch ON ch.id = c.parent_id
                    )
                    SELECT COUNT(*)
                    FROM products p
                    JOIN category c ON p.category_id = c.id
                    JOIN category_hierarchy ch ON ch.id = c.id;
                    """,nativeQuery = true
    )
    Page<ProductEntity> findByCategory(@Param("category") String category,@Param("sort") String sort, Pageable pageable);
}
