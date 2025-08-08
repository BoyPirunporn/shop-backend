package com.backend.shop.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.shop.infrastructure.entity.MenuItemEntity;

public interface MenuItemJpaRepository extends DataTablesRepository<MenuItemEntity, Long> {
    @Query("SELECT m FROM MenuItemEntity m WHERE m.parent IS NULL")
    List<MenuItemEntity> findRootMenusWithChildren();

    @Query("SELECT new com.backend.shop.infrastructure.entity.MenuItemEntity(m.id, m.title, m.url, m.icon, m.sortOrder, m.visible, m.isGroup) " +
       "FROM MenuItemEntity m WHERE LOWER(m.title) LIKE LOWER(CONCAT(:title, '%'))")
    List<MenuItemEntity> findAllByTitleStartingWithIgnoreCase(@Param("title") String title);

    boolean existsByTitle(String title);
}
