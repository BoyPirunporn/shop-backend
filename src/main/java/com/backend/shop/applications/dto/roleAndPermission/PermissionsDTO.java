package com.backend.shop.applications.dto.roleAndPermission;

import com.backend.shop.applications.dto.BaseDTO;
import com.backend.shop.applications.dto.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class PermissionsDTO extends BaseDTO {
    @JsonView({Views.MenuItemDatatable.class,Views.DataTable.class,Views.MenuItem.class,Views.MenuItem.Permission.class})
    private Long id;
    @JsonView({Views.MenuItemDatatable.class,Views.DataTable.class,Views.MenuItem.class,Views.MenuItem.Permission.class})
    private String description;
    @JsonView({Views.MenuItemDatatable.class,Views.DataTable.class,Views.MenuItem.class,Views.MenuItem.Permission.class})
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public PermissionsDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public PermissionsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

}
