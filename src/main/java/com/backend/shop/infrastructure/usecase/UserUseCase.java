package com.backend.shop.infrastructure.usecase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.MenuItem;
import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.RoleMenuPermission;
import com.backend.shop.domains.models.User;
import com.backend.shop.domains.usecase.IUserUseCase;
import com.backend.shop.infrastructure.entity.RoleMenuPermissionEntity;
import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.backend.shop.infrastructure.mapper.AuthMapper;
import com.backend.shop.infrastructure.mapper.RoleMenuPermissionMapper;
import com.backend.shop.infrastructure.repository.PermissionJpaRepository;
import com.backend.shop.infrastructure.repository.RoleMenuPermissionJpaRepository;
import com.backend.shop.infrastructure.repository.UserJapRepository;

import jakarta.transaction.Transactional;

@Service
public class UserUseCase implements IUserUseCase {
    private final UserJapRepository userJapRepository;
    private final RoleMenuPermissionJpaRepository roleMenuPermissionJpaRepository;
    private final RoleMenuPermissionMapper roleMenuPermissionMapper;
    private final PermissionJpaRepository permissionJpaRepository;
    private final AuthMapper authMapper;

    public UserUseCase(UserJapRepository userJapRepository,
            RoleMenuPermissionJpaRepository roleMenuPermissionJpaRepository,
            RoleMenuPermissionMapper roleMenuPermissionMapper, PermissionJpaRepository permissionJpaRepository,
            AuthMapper authMapper) {
        this.userJapRepository = userJapRepository;
        this.roleMenuPermissionJpaRepository = roleMenuPermissionJpaRepository;
        this.roleMenuPermissionMapper = roleMenuPermissionMapper;
        this.permissionJpaRepository = permissionJpaRepository;
        this.authMapper = authMapper;
    }

    @Override
    @Transactional
    public void createUser(User user, Set<RoleMenuPermission> roleMenuPermissions) {
        UsersEntity usersEntity = authMapper.toEntity(user);
        userJapRepository.save(usersEntity);
        Set<RoleMenuPermissionEntity> roleMenuPermissionEntities = roleMenuPermissions.stream()
                .map(roleMenuPermissionMapper::toEntity).collect(Collectors.toSet());
        roleMenuPermissionJpaRepository.saveAll(roleMenuPermissionEntities);
    }

    @Override
    @Cacheable(cacheNames = "get-permission", key = "#name")
    public Permissions findPermissionByName(String name) {
        return permissionJpaRepository.findByName(name).map(authMapper::toModel).orElse(null);
    }

}
