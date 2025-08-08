package com.backend.shop.applications.dto.menu;

import java.util.ArrayList;
import java.util.List;


public class MenuItemDTO {
    private Long id;
    private String title;
    private String url;
    private String icon;
    private Integer sortOrder;
    private Boolean visible;
    private Boolean isGroup;

    private MenuItemDTO parent;

    private List<MenuItemDTO> items = new ArrayList<>();


    public MenuItemDTO(){}
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

    

}
