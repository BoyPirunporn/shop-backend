package com.backend.shop.infrastructure.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 100)
    private String description;
    
    @Column(nullable = false, columnDefinition = "int default 0")
    private int level;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    // private Set<PermissionEntity> permissions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<RoleMenuPermissionEntity> roleMenuPermissions = new HashSet<>();

    public RoleEntity() {
    }

    public RoleEntity(Long id) {
        super(id);
    }

    // public RoleEntity(Long id, String name, Set<PermissionEntity> permissions) {
    //     super(id);
    //     this.name = name;
    //     this.permissions = permissions;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public Set<PermissionEntity> getPermissions() {
    //     return permissions;
    // }

    // public void setPermissions(Set<PermissionEntity> permissions) {
    //     this.permissions = permissions;
    // }

    // @Override
    // public String toString() {
    //     return "RoleEntity [name=" + name + ", permissions=" + "[" + permissions.stream().map(PermissionEntity::getName).toList().toString() + "]" + "]";
    // }
    

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "RoleEntity [name=" + name + ", description=" + description + ", level=" + level + "]";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RoleMenuPermissionEntity> getRoleMenuPermissions() {
        return roleMenuPermissions;
    }

    public void setRoleMenuPermissions(Set<RoleMenuPermissionEntity> roleMenuPermissions) {
        this.roleMenuPermissions = roleMenuPermissions;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
