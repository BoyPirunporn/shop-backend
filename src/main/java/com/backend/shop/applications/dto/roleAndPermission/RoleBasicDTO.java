package com.backend.shop.applications.dto.roleAndPermission;

import com.backend.shop.applications.dto.BaseDTO;
import com.backend.shop.applications.dto.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class RoleBasicDTO extends BaseDTO {
    
    @JsonView({Views.DataTable.class, Views.MenuItem.class})
    private Long id;

    @JsonView({Views.DataTable.class, Views.MenuItem.class})
    private String name;

    @JsonView({Views.DataTable.class, Views.MenuItem.class})
    private String description;

    @JsonView({Views.DataTable.class, Views.MenuItem.class})
    private int level;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    
}