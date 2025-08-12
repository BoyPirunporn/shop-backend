package com.backend.shop.applications.dto.auth;

import com.backend.shop.applications.dto.BaseDTO;
import com.backend.shop.domains.models.AuthProvider;

public class UserAuthProviderDTO extends BaseDTO {
    private AuthProvider provider;
    private String providerUserId;
    private String accessToken;
    private String refreshToken;
    private AuthDTO user;
    
    public AuthProvider getProvider() {
        return provider;
    }
    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }
    public String getProviderUserId() {
        return providerUserId;
    }
    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public AuthDTO getUser() {
        return user;
    }
    public void setUser(AuthDTO user) {
        this.user = user;
    }

    
}
