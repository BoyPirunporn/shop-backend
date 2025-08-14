package com.backend.shop.applications.dto.user;

import java.util.List;


public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<MenuItemRequest> menuItems;
    private List<RoleRequest> roles;
    

    public static class MenuItemRequest {
        private Long id;
        private String title;
        private MenuItemRequest parent;
        private List<PermissionRequest> permissions;

        
        
        @Override
        public String toString() {
            return "MenuItemRequest [id=" + id + ", title=" + title + ", parent=" + parent + ", permissions="
                    + permissions + "]";
        }
        public static class PermissionRequest {
            private Long id;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "PermissionRequest [id=" + id + "]";
            }
            
            
        }
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
        public MenuItemRequest getParent() {
            return parent;
        }
        public void setParent(MenuItemRequest parent) {
            this.parent = parent;
        }
        public List<PermissionRequest> getPermissions() {
            return permissions;
        }
        public void setPermissions(List<PermissionRequest> permissions) {
            this.permissions = permissions;
        }
       
    }
   
    public static class RoleRequest {
        private Long id;
        private String name;
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
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MenuItemRequest> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemRequest> menuItems) {
        this.menuItems = menuItems;
    }

    public List<RoleRequest> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleRequest> roles) {
        this.roles = roles;
    }
}
