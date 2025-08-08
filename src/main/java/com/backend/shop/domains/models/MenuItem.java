package com.backend.shop.domains.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class MenuItem extends BaseModel{
    private String title;
    private String url;
    private String icon;
    private Integer sortOrder;
    private Boolean visible;
    private Boolean isGroup;

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @JsonBackReference
    private MenuItem parent;

    @JsonManagedReference
    private List<MenuItem> items = new ArrayList<>();


     public MenuItem(String title, String url, String icon, Integer sortOrder, MenuItem parent, List<MenuItem> items) {
        this.title = title;
        this.url = url;
        this.icon = icon;
        this.sortOrder = sortOrder;
        this.parent = parent;
        this.items = items;
    }
    public MenuItem(Long id,String title, String url, String icon, Integer sortOrder,boolean visible,boolean isGroup) {
        super(id);
        this.title = title;
        this.url = url;
        this.icon = icon;
        this.sortOrder = sortOrder;
        this.visible = visible;
        this.isGroup = isGroup;
    }

    public MenuItem() {
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

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
    

   

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    
    
}
