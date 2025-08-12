package com.backend.shop.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.shop.infrastructure.entity.UsersEntity;

public interface UserJapRepository extends DataTablesRepository<UsersEntity,Long> {
    
    @Query("SELECT u FROM users u LEFT JOIN FETCH u.roles r  WHERE email = :email")
    public Optional<UsersEntity> findByEmail(@Param("email") String email);
    public boolean existsByEmail(String email);

    long countByRoles_Name(String roleName);

    @Query("SELECT u FROM users u LEFT JOIN FETCH u.roles r  WHERE email = :email")
    public Optional<UsersEntity> findByEmailWithRoles(@Param("email") String email);
}
