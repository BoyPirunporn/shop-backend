package com.backend.shop.applications.dto.menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.backend.shop.applications.dto.Views;
import com.backend.shop.applications.dto.roleAndPermission.RoleMenuPermissionDTO;
import com.fasterxml.jackson.annotation.JsonView;

public class MenuItemDTO {
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class,Views.MenuItemWithOutRoleMenuPermission.class})
    private Long id;
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class,Views.MenuItemWithOutRoleMenuPermission.class})
    private String title;
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class,Views.MenuItemWithOutRoleMenuPermission.class})
    private String url;
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class,Views.MenuItemWithOutRoleMenuPermission.class})
    private String icon;
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class,Views.MenuItemWithOutRoleMenuPermission.class})
    private Integer sortOrder;
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class,Views.MenuItemWithOutRoleMenuPermission.class })
    private Boolean visible;
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class,Views.MenuItemWithOutRoleMenuPermission.class })
    private Boolean isGroup;
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class,Views.MenuItemWithOutRoleMenuPermission.class})
    private MenuItemDTO parent;
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class,Views.MenuItemWithOutRoleMenuPermission.class })
    private List<MenuItemDTO> items = new ArrayList<>();
    @JsonView({ Views.MenuItemDatatable.class, Views.DataTable.class, Views.MenuItem.class })
    private Set<RoleMenuPermissionDTO> roleMenuPermissions = new HashSet<>();

    public MenuItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public MenuItemDTO getParent() {
        return parent;
    }

    public void setParent(MenuItemDTO parent) {
        this.parent = parent;
    }

    public List<MenuItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDTO> items) {
        this.items = items;
    }

    public Set<RoleMenuPermissionDTO> getRoleMenuPermissions() {
        return roleMenuPermissions;
    }

    public void setRoleMenuPermissions(Set<RoleMenuPermissionDTO> roleMenuPermissions) {
        this.roleMenuPermissions = roleMenuPermissions;
    }

}
