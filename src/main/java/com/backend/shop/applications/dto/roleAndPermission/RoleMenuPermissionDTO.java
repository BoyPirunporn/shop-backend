package com.backend.shop.applications.dto.roleAndPermission;

import com.backend.shop.applications.dto.Views;
import com.backend.shop.applications.dto.menu.MenuItemDTO;
import com.fasterxml.jackson.annotation.JsonView;

public class RoleMenuPermissionDTO {
    @JsonView({Views.MenuItemDatatable.class,Views.DataTable.class,Views.MenuItem.class})
    private Long id;
    @JsonView({Views.MenuItemDatatable.class,Views.DataTable.class,Views.MenuItem.Role.class})
    private RoleDTO role;
    @JsonView({Views.MenuItemDatatable.class,Views.DataTable.class,Views.MenuItem.class, Views.MenuItem.Permission.class})
    private PermissionsDTO permission;

    @JsonView({Views.DataTable.class,Views.MenuItem.Full.class})
    private MenuItemDTO menuItem;

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public PermissionsDTO getPermission() {
        return permission;
    }

    public void setPermission(PermissionsDTO permission) {
        this.permission = permission;
    }

    public MenuItemDTO getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemDTO menuItem) {
        this.menuItem = menuItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
