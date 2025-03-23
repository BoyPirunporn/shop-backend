package com.backend.shop.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.shop.infrastructure.entity.UsersEntity;

public interface UserJapRepoitory extends JpaRepository<UsersEntity,Long> {
    
    public Optional<UsersEntity> findByEmail(String email);
    public boolean existsByEmail(String email);

    @Query(value = """
            SELECT COUNT(*) FROM users u WHERE 'USER' = ANY(u.roles)
            """,nativeQuery = true)
    public Long countRoleUser();
    @Query(value = """
            SELECT COUNT(*) FROM users u WHERE 'ADMIN' = ANY(u.roles)
            """,nativeQuery = true)
    public Long countRoleAdmin();
}
