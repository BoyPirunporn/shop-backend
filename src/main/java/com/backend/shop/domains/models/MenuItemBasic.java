package com.backend.shop.domains.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class MenuItemBasic  extends BaseModel {
     private String title;
    private String url;
    private String icon;
    private Integer sortOrder;
    private Boolean visible;
    private Boolean isGroup;


    @JsonBackReference
    private MenuItem parent;

    @JsonManagedReference
    private List<MenuItem> items = new ArrayList<>();

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

    @Override
    public String toString() {
        return "MenuItemBasic [id=" + getId() + ", title=" + title + ", url=" + url + ", icon=" + icon + ", sortOrder=" + sortOrder
                + ", visible=" + visible + ", isGroup=" + isGroup + ", parent=" + parent+ ", items=" + items + "]";
    }

    
    
}
