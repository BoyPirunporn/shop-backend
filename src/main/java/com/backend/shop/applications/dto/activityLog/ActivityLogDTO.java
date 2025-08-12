package com.backend.shop.applications.dto.activityLog;

import java.util.HashSet;
import java.util.Set;

import com.backend.shop.applications.dto.BaseDTO;
import com.backend.shop.applications.dto.roleAndPermission.RoleDTO;

public class ActivityLogDTO extends BaseDTO {
    private Long id;
    private User user;
    private String action;
    private String target;
    private String metadata;
    private String ipAddress;
    private String userAgent;

    public ActivityLogDTO() {
    }

    public static class User {
        private Long id;
        private String email;
        private String firstName;
        private String lastName;
        private Set<RoleDTO> roles = new HashSet<>();

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

        public Set<RoleDTO> getRoles() {
            return roles;
        }

        public void setRoles(Set<RoleDTO> roles) {
            this.roles = roles;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "ActivityLogDTO [id=" + id + ", action=" + action + ", target=" + target + ", metadata=" + metadata
                + ", ipAddress=" + ipAddress + ", userAgent=" + userAgent + "]";
    }

    public ActivityLogDTO(String action, String target, String metadata, String ipAddress, String userAgent) {
        this.action = action;
        this.target = target;
        this.metadata = metadata;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    
    
}
