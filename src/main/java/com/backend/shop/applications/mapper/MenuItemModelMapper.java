package com.backend.shop.applications.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.backend.shop.applications.dto.menu.MenuItemDTO;
import com.backend.shop.applications.dto.user.UserRequest;
import com.backend.shop.domains.models.MenuItem;
import com.backend.shop.domains.models.MenuItemBasic;
import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.RoleMenuPermission;

@Mapper(componentModel = "spring", uses = { RoleMenuPermissionModelMapper.class })
public interface MenuItemModelMapper {

    MenuItem toModel(MenuItemDTO dto);

    MenuItemDTO toDTO(MenuItem model);

    MenuItemDTO toDTO(MenuItemBasic model);

    @Named("toDTOIgnoreParentAndItem")
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "parent", ignore = true)
    MenuItemDTO toDTOIgnoreParentAndItem(MenuItem model);

    @Named("mapMenuItemRequestToMenuItemModel")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "parent", target = "parent", qualifiedByName = "mapParentToMenuItemModel")
    @Mapping(source = "permissions", target = "roleMenuPermissions", qualifiedByName = "mapPermissionRequestToRoleMenuPermissionModel")
    MenuItem mapMenuItemRequestToMenuItemModel(UserRequest.MenuItemRequest menuItemDTO);


    @Named("mapParentToMenuItemModel")
    default MenuItem mapParentToMenuItemModel(UserRequest.MenuItemRequest menuItemDTO) {
        if (menuItemDTO == null) {
            return null;
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemDTO.getId());
        menuItem.setTitle(menuItemDTO.getTitle());
        if (menuItemDTO.getParent() != null) {
            menuItem.setParent(mapParentToMenuItemModel(menuItemDTO.getParent()));
        }

        return menuItem;
    }

    @Named("mapPermissionRequestToRoleMenuPermissionModel")
    default Set<RoleMenuPermission> mapPermissionRequestToRoleMenuPermissionModel(
            List<UserRequest.MenuItemRequest.PermissionRequest> permissions) {
        if (permissions == null) {
            return Collections.emptySet();
        }
        return permissions.stream().map(e -> {

            RoleMenuPermission roleMenuPermission = new RoleMenuPermission();
            roleMenuPermission.setPermission(new Permissions(e.getId()));
            return roleMenuPermission;
        }).collect(Collectors.toSet());
    }

}
