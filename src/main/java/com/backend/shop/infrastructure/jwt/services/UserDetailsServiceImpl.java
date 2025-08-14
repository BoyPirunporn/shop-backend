package com.backend.shop.infrastructure.jwt.services;

import java.util.Collection;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.repository.UserJapRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserJapRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "users",key = "#email")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("LOAD BY Email -> {}" ,email);

        UsersEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // ✅ รองรับ Multiple Roles
        // Collection<? extends GrantedAuthority> authorities =  user.getAuthorities();

                //log.info("AUTH : "+authorities.toString());
        return user;
    }
}
