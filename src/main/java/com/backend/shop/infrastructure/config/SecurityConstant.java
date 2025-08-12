package com.backend.shop.infrastructure.config;

import java.util.List;

public class SecurityConstant {
    public static final List<String> PUBLIC_ROUTE = List.of(
            "/files/**",
            "/api/v1/auth/sign-up",
            "/api/v1/auth/sign-in",
            "/api/v1/auth/refresh-token",
            "/swagger-ui/**",
            "/swagger-ui.html", // ✅ เพิ่ม Swagger UI HTML
            "/v3/api-docs/**", // ✅ อนุญาต API Docs ทั้งหมด
            "/api/v1/products/**",
            "/api/v1/category/**",
            "/api/v1/menu/**"
    );
}
