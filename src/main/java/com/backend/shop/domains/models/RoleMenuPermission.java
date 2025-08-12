package com.backend.shop.domains.models;

public class RoleMenuPermission extends BaseModel {
    private Role role;
    private MenuItem menuItem;
    private Permissions permission;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public MenuItem getMenuItem() {
        return menuItem;
    }
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
    public Permissions getPermission() {
        return permission;
    }
    public void setPermission(Permissions permission) {
        this.permission = permission;
    }
    

    
}
