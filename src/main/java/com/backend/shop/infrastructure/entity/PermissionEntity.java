package com.backend.shop.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "permissions")
public class PermissionEntity  extends BaseEntity{

    @Column(nullable = false,unique = true,length = 100)
    private String name;
    @Column(length = 100)
    private String description;

    

    public PermissionEntity(String name,String description) {
        this.name = name;
        this.description = description;
    }

    public PermissionEntity(Long id, String name,String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public PermissionEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
