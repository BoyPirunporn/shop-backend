package com.backend.shop.infrastructure.jwt.services;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.repository.UserJapRepoitory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserJapRepoitory userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        
        // ✅ รองรับ Multiple Roles
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());

                System.out.println("AUTH : "+authorities.toString());
        return new User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}
