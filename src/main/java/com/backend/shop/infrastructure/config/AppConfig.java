package com.backend.shop.infrastructure.config;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = "com.backend.shop.infrastructure.repository")
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

   @Bean
public KeyGenerator customKeyGenerator() {
    return (target, method, params) -> {
        DataTablesInput input = (DataTablesInput) params[0];

        // global search value
        String globalSearch = input.getSearch() != null ? input.getSearch().getValue() : "";

        // columns + column search values
        String columnsKey = input.getColumns().stream()
            .map(col -> col.getData() + "=" + (col.getSearch() != null ? col.getSearch().getValue() : ""))
            .sorted() // เรียงลำดับ เพื่อให้ key เป็น deterministic
            .collect(Collectors.joining(","));

        log.info(columnsKey);

        int key = Objects.hash(
            input.getDraw(),
            input.getStart(),
            input.getLength(),
            globalSearch,
            columnsKey
        );
        System.out.println("KEY -> " + key);
        // รวม fields สำคัญทั้งหมดเป็น key
        return key;
    };
}

}
