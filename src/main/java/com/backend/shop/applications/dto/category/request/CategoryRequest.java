package com.backend.shop.applications.dto.category.request;

import java.util.List;

public class CategoryRequest {
    private Long id;
    private String name;
    private Object imageUrl;
    private Long parentId;
    private List<CategoryRequest> children;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Object getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public List<CategoryRequest> getChildren() {
        return children;
    }
    public void setChildren(List<CategoryRequest> children) {
        this.children = children;
    }

    
}
