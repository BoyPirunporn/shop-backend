package com.backend.shop.infrastructure.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_item")
public class MenuItemEntity extends BaseEntity {

    private String title;
    private String url;
    private String icon;
    private Integer sortOrder;
    @Column(name = "visible")
    private Boolean visible = true;
    private Boolean isGroup = false;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private MenuItemEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    @JsonManagedReference
    private List<MenuItemEntity> items = new ArrayList<>();

    
    
    @OneToMany(mappedBy = "menuItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<RoleMenuPermissionEntity> roleMenuPermissions = new HashSet<>();

     public MenuItemEntity() {
    }

    public MenuItemEntity(String title, String url, String icon, Integer sortOrder, Boolean visible,
            MenuItemEntity parent, List<MenuItemEntity> items) {
        this.title = title;
        this.url = url;
        this.icon = icon;
        this.sortOrder = sortOrder;
        this.visible = visible;
        this.parent = parent;
        this.items = items;
    }
    public MenuItemEntity(Long id,String title, String url, String icon, Integer sortOrder, boolean visible,boolean isGroup) {
        super(id);
        this.title = title;
        this.url = url;
        this.icon = icon;
        this.sortOrder = sortOrder;
        this.visible = visible;
        this.isGroup = isGroup;
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

    public MenuItemEntity getParent() {
        return parent;
    }

    public void setParent(MenuItemEntity parent) {
        this.parent = parent;
    }

    public List<MenuItemEntity> getItems() {
        return items;
    }

    public void setItems(List<MenuItemEntity> items) {
        this.items = items;
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
    

    @Override
    public String toString() {
        return "MenuItemEntity [title=" + title + ", url=" + url + ", icon=" + icon + ", sortOrder=" + sortOrder
                + ", visible=" + visible + ", isGroup=" + isGroup + "]";
    }

    public Set<RoleMenuPermissionEntity> getRoleMenuPermissions() {
        return roleMenuPermissions;
    }

    public void setRoleMenuPermissions(Set<RoleMenuPermissionEntity> roleMenuPermissions) {
        this.roleMenuPermissions = roleMenuPermissions;
    }

    
}
