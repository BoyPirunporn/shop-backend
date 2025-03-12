package com.backend.shop.applications.dto.auth;

import java.util.Set;

import com.backend.shop.domains.enums.ERole;

public class TokenDTO {
    private String token;
    private String refreshToken;
    private Set<ERole> roles;
    
    public TokenDTO() {
    }
    public TokenDTO(String token, String refreshToken, Set<ERole> roles) {
        this.token = token;
        this.roles = roles;
        this.refreshToken = refreshToken;
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
    public Set<ERole> getRoles() {
        return roles;
    }
    public void setRoles(Set<ERole> roles) {
        this.roles = roles;
    }

    

    
}
