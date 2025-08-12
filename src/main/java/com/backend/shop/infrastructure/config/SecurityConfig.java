package com.backend.shop.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
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
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(SecurityConstant.PUBLIC_ROUTE.toArray(new String[0])).permitAll()
                        .requestMatchers(
                                "/api/v1/admin/**","/api/v1/auth/datatable").authenticated().anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filterJwt, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .exceptionHandling((accessDenied) -> {
                    accessDenied.accessDeniedHandler((request, response, accessDeniedException) -> {
                        log.info("MESSAGE ACCESS DENIED : " + request.getRequestURI());
                        log.info("MESSAGE ACCESS DENIED : " + accessDeniedException.getMessage());
                        accessDeniedException.printStackTrace();
                        response.setStatus(403);
                        response.getWriter().write(accessDeniedException.getMessage());
                    });
                });
        return http.build();
    }

}
