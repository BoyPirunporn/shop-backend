package com.backend.shop.infrastructure.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.shop.infrastructure.jwt.exceptions.JwtException;
import com.backend.shop.infrastructure.repository.UserJapRepoitory;

@Configuration
public class AppConfig {

    private final UserJapRepoitory userJapRepoitory;

    public AppConfig(UserJapRepoitory userJapRepoitory) {
        this.userJapRepoitory = userJapRepoitory;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return u -> (UserDetails) userJapRepoitory.findByEmail(u)
                .orElseThrow(() -> new JwtException("Email or password not found!!", HttpStatus.BAD_REQUEST));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder()); // ตรวจสอบว่าคุณมี PasswordEncoder
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return new ProviderManager(List.of(authenticationProvider(userDetailsService())));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
