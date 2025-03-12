package com.backend.shop.applications.dto.auth;

import jakarta.validation.constraints.NotBlank;

public class SignInDTO {
    @NotBlank(message = "Must not be null or empty")
    private String email;
    @NotBlank(message = "Must not be null or empty")
    private String password;
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

    
}
