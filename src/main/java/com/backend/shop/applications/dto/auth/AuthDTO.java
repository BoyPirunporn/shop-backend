package com.backend.shop.applications.dto.auth;

import java.util.HashSet;
import java.util.Set;

import com.backend.shop.applications.dto.roleAndPermission.RoleDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDTO {
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private String firstName;
    private String lastName;
    private String image;
    private Set<UserAuthProviderDTO> authProviders;

    
    



    // @RoleValidator(message = "Role must be [ USER, ADMIN, SUPER_ADMIN ]")
    private Set<RoleDTO> roles = new HashSet<>();

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

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {

        this.roles = roles;
    }

    
    @Override
    public String toString() {
        return "AuthDTO [email=" + email + ", password=" + password + ", roles=" + roles + "]";
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


    public Set<UserAuthProviderDTO> getAuthProviders() {
        return authProviders;
    }

    public void setAuthProviders(Set<UserAuthProviderDTO> authProvider) {
        this.authProviders = authProvider;
    }

}
