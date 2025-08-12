package com.backend.shop.domains.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuItem extends MenuItemBasic {

    private Set<RoleMenuPermission> roleMenuPermissions = new HashSet<>();

    public MenuItem(String title, String url, String icon, Integer sortOrder, MenuItem parent, List<MenuItem> items) {
        setTitle(title);
        setUrl(url);
        setIcon(icon);
        setSortOrder(sortOrder);
        setParent(parent);
        setItems(items);
    }

    public MenuItem(Long id, String title, String url, String icon, Integer sortOrder, boolean visible,
            boolean isGroup) {
        setId(id);
        setTitle(title);
        setUrl(url);
        setIcon(icon);
        setSortOrder(sortOrder);
    }

    public MenuItem() {
    }

    public Set<RoleMenuPermission> getRoleMenuPermissions() {
        return roleMenuPermissions;
    }

    public void setRoleMenuPermissions(Set<RoleMenuPermission> roleMenuPermissions) {
        this.roleMenuPermissions = roleMenuPermissions;
    }

}
