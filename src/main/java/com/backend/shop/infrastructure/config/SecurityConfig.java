package com.backend.shop.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.shop.infrastructure.jwt.interfaces.IFilterJwt;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final IFilterJwt filterJwt;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(IFilterJwt filterJwt, AuthenticationProvider authorize) {
        this.filterJwt = filterJwt;
        this.authenticationProvider = authorize;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(
                        "/api/v1/auth/**", // ✅ ต้องมี / นำหน้า
                        "/swagger-ui/**", // ✅ ต้องมี / นำหน้า
                        "/v3/api-docs", // ✅ ต้องมี / นำหน้า
                        "/v3/api-docs/swagger-config"
                        ).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filterJwt, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);
        return http.build();
    }

}
