package com.backend.shop.domains.models;

public class Permissions  extends BaseModel{
    private String name;
    private String description;
    

    public Permissions() {
    }
    

    public Permissions(Long id, String name,String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public Permissions(String name,String description) {
        this.name = name;
        this.description = description;
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
