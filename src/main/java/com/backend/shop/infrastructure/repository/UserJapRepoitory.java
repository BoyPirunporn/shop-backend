package com.backend.shop.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.shop.infrastructure.entity.UsersEntity;

public interface UserJapRepoitory extends JpaRepository<UsersEntity,Long> {
    
    public Optional<UsersEntity> findByEmail(String email);
    public boolean existsByEmail(String email);
}
