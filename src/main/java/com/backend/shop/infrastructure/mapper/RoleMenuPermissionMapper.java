package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.RoleMenuPermission;
import com.backend.shop.infrastructure.entity.RoleMenuPermissionEntity;

@Mapper(componentModel = "spring")
public interface RoleMenuPermissionMapper {

    @Mapping(target = "role.roleMenuPermissions", ignore = true)
    @Mapping(target = "menuItem.roleMenuPermissions", ignore = true)
    @Mapping(target = "menuItem.parent", ignore = true)
    @Mapping(target = "menuItem.items", ignore = true)
    RoleMenuPermission toModel(RoleMenuPermissionEntity entity);

    @Mapping(target = "role.roleMenuPermissions", ignore = true)
    @Mapping(target = "menuItem.roleMenuPermissions", ignore = true)
    @Mapping(target = "menuItem.parent", ignore = true)
    @Mapping(target = "menuItem.items", ignore = true)
    RoleMenuPermissionEntity toEntity(RoleMenuPermission model);
}
