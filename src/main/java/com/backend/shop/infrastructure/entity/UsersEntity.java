package com.backend.shop.infrastructure.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity(name = "users")
public class UsersEntity extends BaseEntity implements UserDetails {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String image;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<UserAuthProviderEntity> authProviders = new HashSet<>();
    

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

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

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        System.out.println("ROLE -> {}"+roles.size());
        // ดึง ROLE_<NAME>
        authorities.addAll(
                roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .collect(Collectors.toSet()));

        // ดึง Permission ของแต่ละ Role
        // authorities.addAll(
        //         roles.stream()
        //                 .flatMap(role -> role.getPermissions().stream())
        //                 .map(permission -> new SimpleGrantedAuthority(permission.getName()))
        //                 .collect(Collectors.toSet()));

        return authorities;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
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

    @Override
    public String toString() {
        return "UsersEntity [id=" + getId() + ", email=" + email + ", password=" + password + ", firstName=" + firstName
                + ", lastName="
                + lastName + ", image=" + image + "roles=" + "[" + roles.stream().map(RoleEntity::getName).toList().toString() + "]"
                + ""+"]";
    }
// , roles=" + roles + "
    public Set<UserAuthProviderEntity> getAuthProviders() {
        return authProviders;
    }

    public void setAuthProviders(Set<UserAuthProviderEntity> authProviders) {
        this.authProviders = authProviders;
    }

}
