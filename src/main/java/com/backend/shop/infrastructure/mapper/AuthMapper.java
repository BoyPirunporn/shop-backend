package com.backend.shop.infrastructure.mapper;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.Role;
import com.backend.shop.domains.models.RoleBasic;
import com.backend.shop.domains.models.User;
import com.backend.shop.domains.models.UserAuthProvider;
import com.backend.shop.infrastructure.entity.PermissionEntity;
import com.backend.shop.infrastructure.entity.RoleEntity;
import com.backend.shop.infrastructure.entity.UserAuthProviderEntity;
import com.backend.shop.infrastructure.entity.UsersEntity;

@Mapper(componentModel = "spring",uses = {RoleMenuPermissionMapper.class})
public interface AuthMapper {

    User toModel(UsersEntity entity);

    @Mapping(target = "roles",ignore = true)
    @Mapping(source = "authProviders", target = "authProviders",qualifiedByName  = "authProviderWithoutUser")
    @Mapping(target = "password",ignore = true)
    User toModelWithOutRole(UsersEntity entity);

    @Named("authProviderWithoutUser")
    default Set<UserAuthProvider> authProviderWithoutUser(Set<UserAuthProviderEntity> entities ){
        Set<UserAuthProvider> userAuthProviders = new HashSet<>();
        entities.stream().forEach(u -> {
            UserAuthProvider userProvider = new UserAuthProvider();
            userProvider.setAccessToken(u.getAccessToken());
            userProvider.setId(u.getId());
            userProvider.setProvider(u.getProvider());
            userProvider.setProviderUserId(u.getProviderUserId());
            userProvider.setCreatedAt(u.getCreatedAt());
            userProvider.setUpdatedAt(u.getUpdatedAt());
            userAuthProviders.add(userProvider);
        });
        return userAuthProviders;
    }

    UsersEntity toEntity(User model);

    Role toModel(RoleEntity entity);

    RoleBasic toBasicModel(RoleEntity entity);

    RoleEntity toEntity(Role model);

    Permissions toModel(PermissionEntity entity);

    PermissionEntity toEntity(Permissions model);

    @Mapping(target = "user.authProviders", ignore = true)
    @Mapping(target = "user.roles", ignore = true)
    UserAuthProvider toModel(UserAuthProviderEntity entity);

    @Mapping(target = "user.authProviders", ignore = true)
    @Mapping(target = "user.roles", ignore = true)
    UserAuthProviderEntity toEntity(UserAuthProvider model);
}