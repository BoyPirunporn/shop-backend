package com.backend.shop.infrastructure.repository;

import com.backend.shop.infrastructure.entity.RoleMenuPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleMenuPermissionJpaRepository extends JpaRepository<RoleMenuPermissionEntity, Long> {
    @Modifying
    @Query("DELETE FROM RoleMenuPermissionEntity rmp WHERE rmp.role.id = :roleId")
    void deleteByRoleId(@Param("roleId") Long roleId);
}
