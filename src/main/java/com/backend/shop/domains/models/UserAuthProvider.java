package com.backend.shop.domains.models;


public class UserAuthProvider extends BaseModel {
    private AuthProvider provider;
    private String providerUserId;
    private String accessToken;
    private String refreshToken;
    private User user;
   
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public UserAuthProvider(Long id, AuthProvider provider, String providerUserId, String accessToken,
            String refreshToken, User user) {
        setId(id);
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }
    public UserAuthProvider() {
    }
    

    
    
}
