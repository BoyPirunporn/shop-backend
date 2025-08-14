package com.backend.shop.domains.models;

import java.util.HashSet;
import java.util.Set;


public class Role extends RoleBasic{
    
    // private Set<Permissions> permissions = new HashSet<>();
    private Set<RoleMenuPermission> roleMenuPermissions = new HashSet<>();
    // public Set<Permissions> getPermissions() {
    //     return permissions;
    // }
    // public void setPermissions(Set<Permissions> permissions) {
    //     this.permissions = permissions;
    // }
    public Set<RoleMenuPermission> getRoleMenuPermissions() {
        return roleMenuPermissions;
    }
    public void setRoleMenuPermissions(Set<RoleMenuPermission> roleMenuPermissions) {
        this.roleMenuPermissions = roleMenuPermissions;
    }
    public Role() {
    }
    public Role(Long id, String name, String description, int level) {
        super(id, name, description, level);
    }
    @Override
    public String toString() {
        return "Role [roleMenuPermissions=" + roleMenuPermissions + "]";
    }

    
    
    
}
