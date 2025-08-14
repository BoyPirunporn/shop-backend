package com.backend.shop.domains.models;

import java.util.Set;
import java.util.stream.Collectors;

public class User extends BaseModel{
    
   
    private String email;
    private String firstName;
    private String lastName;
    private String image;
    private String password;
    private Set<Role> roles;
    private Set<UserAuthProvider> authProviders;

    
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(Long id, String email, String password, Set<Role> roles) {
        setId(id);
        setEmail(email);
        setPassword(password);
        setRoles(roles);
    }
    public User(String email, String password, Set<Role> roles) {
       setEmail(email);
        setPassword(password);
        setRoles(roles);
    }

    public User() {
    }
    
    public Set<UserAuthProvider> getAuthProviders() {
        return authProviders;
    }
    public void setAuthProviders(Set<UserAuthProvider> authProviders) {
        this.authProviders = authProviders;
    }

    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", email=" + email + ", password=" + password + "]";
    }
    
    public String toRoleString(){
        return roles.stream()
            .map(Role::toString) // หรือ r -> r.getName() ถ้า Role มี name
            .collect(Collectors.joining(", ")).toString(); // รวมด้วย ", "
    }
    
}
