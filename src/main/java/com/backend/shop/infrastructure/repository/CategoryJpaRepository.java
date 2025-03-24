package com.backend.shop.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.shop.infrastructure.entity.CategoryEntity;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity,Long> {
//    @Query("SELECT c FROM CategoryEntity c WHERE c.name LIKE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Optional<CategoryEntity> findByNameContainingIgnoreCase(@Param("name") String name);
    Optional<CategoryEntity> findFirstByParentId(Long id);

    List<CategoryEntity> findAllByParentIsNull();
    List<CategoryEntity> findAllByParentIsNull(Pageable pageable);

    @Query(value = """
            SELECT *,(SELECT name FROM category WHERE id = c.parent_id) as root FROM category c WHERE parent_id = (SELECT id FROM category WHERE parent_id IS NULL)
            """,nativeQuery = true)
    List<CategoryEntity> findAllByParent();

}
