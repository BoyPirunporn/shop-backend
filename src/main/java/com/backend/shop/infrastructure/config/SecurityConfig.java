package com.backend.shop.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.shop.domains.enums.ERole;
import com.backend.shop.infrastructure.jwt.interfaces.IFilterJwt;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    private final IFilterJwt filterJwt;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(IFilterJwt filterJwt, AuthenticationProvider authorize) {
        this.filterJwt = filterJwt;
        this.authenticationProvider = authorize;
    }

    private String[] PUBLIC_ROUTE = {
            "/files/**",
            "/api/v1/auth/**",
            "/swagger-ui/**",
            "/swagger-ui.html", // ✅ เพิ่ม Swagger UI HTML
            "/v3/api-docs/**", // ✅ อนุญาต API Docs ทั้งหมด
            "/api/v1/products/**",
            "/api/v1/category/**",
            "/api/v1/menu/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PUBLIC_ROUTE).permitAll()
                        .requestMatchers(
                                "/api/v1/admin/**")
                        .hasAnyAuthority(ERole.ADMIN.name())
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filterJwt, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .exceptionHandling((accessDenied) -> {
                    accessDenied.accessDeniedHandler((request, response, accessDeniedException) -> {
                        //log.info("MESSAGE ACCESS DENIED : " + accessDeniedException.getMessage());
                        accessDeniedException.printStackTrace();
                        response.setStatus(403);
                        response.getWriter().write(accessDeniedException.getMessage());
                    });
                });
        return http.build();
    }

}
