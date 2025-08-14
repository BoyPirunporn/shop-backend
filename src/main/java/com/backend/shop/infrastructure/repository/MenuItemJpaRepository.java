package com.backend.shop.infrastructure.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.shop.infrastructure.entity.MenuItemEntity;

public interface MenuItemJpaRepository extends DataTablesRepository<MenuItemEntity, Long> {
        @Query("SELECT DISTINCT m FROM MenuItemEntity m  WHERE m.parent IS NULL")
        List<MenuItemEntity> findRootMenusWithChildren();

        @Query("SELECT DISTINCT m FROM MenuItemEntity m LEFT JOIN FETCH m.items c JOIN RoleMenuPermissionEntity rmp ON rmp.menuItem = m WHERE m.parent IS NULL AND rmp.role.id = :role_id")
        List<MenuItemEntity> findRootMenusWithChildren(@Param("role_id") Long roleId);

        @Query("""
                            SELECT DISTINCT m FROM MenuItemEntity m
                        JOIN m.roleMenuPermissions rmp
                        WHERE rmp.role.id IN :roleIds
                        AND m.visible = true
                        AND m.parent.id IS NULL
                        ORDER BY m.sortOrder ASC
                        """)
        List<MenuItemEntity> findRootMenusWithChildren(@Param("roleIds") Set<Long> roleId);

        List<MenuItemEntity> findDistinctByVisibleIsTrueAndRoleMenuPermissions_Role_IdInOrderBySortOrderAsc(
                @Param("roleIds") Set<Long> roleIds);
                
        List<MenuItemEntity> findDistinctByRoleMenuPermissions_Role_IdInOrderBySortOrderAsc(
                @Param("roleIds") Set<Long> roleIds);

        @Query("""
                        SELECT
                        new com.backend.shop.infrastructure.entity.MenuItemEntity(m.id, m.title, m.url, m.icon, m.sortOrder, m.visible, m.isGroup)
                        FROM MenuItemEntity m
                        WHERE LOWER(m.title) LIKE LOWER(CONCAT(:title, '%'))
                                                """)
        List<MenuItemEntity> findAllByTitleStartingWithIgnoreCase(@Param("title") String title);


        List<MenuItemEntity> findAllByParentIsNull();
        boolean existsByTitle(String title);
}
