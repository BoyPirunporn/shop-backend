package com.backend.shop.infrastructure.usecase;

import java.beans.Transient;

import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.User;
import com.backend.shop.domains.usecase.IAuthUsecase;
import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.mapper.AuthMapper;
import com.backend.shop.infrastructure.repository.UserJapRepoitory;

@Service
public class AuthUsecase implements IAuthUsecase {

    private final AuthMapper authMapper;
    private final UserJapRepoitory userJapRepoitory;

    public AuthUsecase(UserJapRepoitory userJapRepoitory, AuthMapper authMapper) {
        this.userJapRepoitory = userJapRepoitory;
        this.authMapper = authMapper;
    }

    @Override
    @Transient
    public User createUser(User user) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setEmail(user.getEmail());
        usersEntity.setPassword(user.getPassword());
        usersEntity.setRoles(user.getRoles());
        UsersEntity saveUser = userJapRepoitory.save(usersEntity);
        return authMapper.toModel(saveUser);
    }

    @Override
    public boolean existingByEmail(String email) {
       return userJapRepoitory.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
      return authMapper.toModel(userJapRepoitory.findByEmail(email).orElse(null));
    }
    
}
