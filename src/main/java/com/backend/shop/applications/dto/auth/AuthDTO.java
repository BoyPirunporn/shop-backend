package com.backend.shop.applications.dto.auth;

import java.util.HashSet;
import java.util.Set;

import com.backend.shop.domains.enums.ERole;
import com.backend.shop.presentation.validators.anotationValidate.RoleValidator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @RoleValidator(message = "Role must be [ ROLE_USER, ROLE_ADMIN, ROLE_SUPER_ADMIN ]")
    private Set<ERole> roles = new HashSet<>();

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

    @Override
    public String toString() {
        return "AuthDTO [email=" + email + ", password=" + password + ", roles=" + roles + "]";
    }

}
