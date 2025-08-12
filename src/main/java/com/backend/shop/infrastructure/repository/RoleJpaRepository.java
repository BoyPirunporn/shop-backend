package com.backend.shop.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.backend.shop.infrastructure.entity.RoleEntity;

public interface RoleJpaRepository extends DataTablesRepository<RoleEntity,Long> {
    List<RoleEntity> findAllByNameStartingWithIgnoreCaseAndLevelLessThanEqual(String name, int level);
    List<RoleEntity> findAllByLevelGreaterThanEqual(int level);
}
