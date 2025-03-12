package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.User;
import com.backend.shop.infrastructure.entity.UsersEntity;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    User toModel(UsersEntity entity);

    UsersEntity toEntity(User model);
}