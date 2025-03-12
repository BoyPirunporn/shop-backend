package com.backend.shop.domains.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ERole {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_SUPER_ADMIN;

    @JsonCreator
    public static ERole fromString(String role) {
        try {
            return ERole.valueOf(role); // แปลง string เป็น ERole
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    @JsonValue
    public String getRole() {
        return this.name();
    }
}
