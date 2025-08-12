package com.backend.shop.domains.models;

public class RoleBasic extends BaseModel {
    private String name;
    private String description;
    private int level;
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
    public RoleBasic(Long id, String name, String description, int level) {
        super(id);
        this.name = name;
        this.description = description;
        this.level = level;
    }
    public RoleBasic() {
    }
    
    
}
