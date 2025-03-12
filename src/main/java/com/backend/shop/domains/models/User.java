package com.backend.shop.domains.models;

import java.util.Set;

import com.backend.shop.domains.enums.ERole;

public class User {
    private Long id;
    private String email;
    private String password;
    private Set<ERole> roles;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Set<ERole> getRoles() {
        return roles;
    }
    public void setRoles(Set<ERole> roles) {
        this.roles = roles;
    }

    public User(Long id, String email, String password, Set<ERole> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public User(String email, String password, Set<ERole> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", roles=" + roles + "]";
    }
    
}
