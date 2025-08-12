package com.backend.shop.applications.dto.auth;

import java.util.ArrayList;
import java.util.List;

import com.backend.shop.applications.dto.menu.MenuItemDTO;

public class TokenDTO {
    private String token;
    private String refreshToken;
    private List<String> roles = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String image;
    private List<MenuItemDTO> menus = new ArrayList<>();

    
    
    public TokenDTO() {
    }

    public TokenDTO(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public TokenDTO(String token, String refreshToken, List<String> roles) {
        setToken(token);
        setRefreshToken(refreshToken);
        setRoles(roles);
    }

    public TokenDTO(String token, String refreshToken, List<String> roles, String firstName, String lastName,
            String image,List<MenuItemDTO> menus) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.menus = menus;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<MenuItemDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuItemDTO> menus) {
        this.menus = menus;
    }
    
    
}
