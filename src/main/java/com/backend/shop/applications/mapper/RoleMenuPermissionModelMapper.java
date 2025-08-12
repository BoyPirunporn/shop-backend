package com.backend.shop.applications.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.applications.dto.roleAndPermission.RoleMenuPermissionDTO;
import com.backend.shop.domains.models.RoleMenuPermission;

@Mapper(componentModel = "spring")
public interface RoleMenuPermissionModelMapper {

    // @Mapping(target = "role.roleMenuPermissions", ignore = true)
    // @Mapping(target = "role.permissions", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "menuItem.roleMenuPermissions", ignore = true)
    @Mapping(target = "menuItem.parent", ignore = true)
    @Mapping(target = "menuItem.items", ignore = true)
    RoleMenuPermissionDTO toDTO(RoleMenuPermission model);

    @Mapping(target = "role.roleMenuPermissions", ignore = true)
    @Mapping(target = "menuItem.roleMenuPermissions", ignore = true)
    @Mapping(target = "menuItem.parent", ignore = true)
    @Mapping(target = "menuItem.items", ignore = true)
    RoleMenuPermission toModel(RoleMenuPermissionDTO dto);
}
