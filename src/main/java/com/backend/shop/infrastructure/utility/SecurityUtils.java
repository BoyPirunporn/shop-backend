package com.backend.shop.infrastructure.utility;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.exceptions.BaseException;

public class SecurityUtils {
    private static final Logger log = LoggerFactory.getLogger(SecurityUtils.class);

    public static String getCurrentUser() {
        Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
        if (authenticator != null && authenticator.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authenticator.getPrincipal()).getUsername();
        }
        throw new BaseException("Unauthorization", HttpStatus.UNAUTHORIZED);
    }

    public static UsersEntity getCurrentUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out
                .println("Not null " + authentication != null && authentication.getPrincipal() instanceof UserDetails);

        if (authentication != null && authentication.getPrincipal() instanceof UsersEntity) {
            return (UsersEntity) authentication.getPrincipal();
        }
        return null;
    }

    public static Collection<? extends GrantedAuthority> getCurrentRolePermission() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UsersEntity) {
            UsersEntity user = (UsersEntity) authentication.getPrincipal();
            return user.getAuthorities();
        }
        return null;
    }

    public static int getCurrentRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UsersEntity) {
            UsersEntity user = (UsersEntity) authentication.getPrincipal();
            return user.getRoles().stream().mapToInt(role -> role.getLevel()).min().orElse(Integer.MAX_VALUE);
        }
        throw new BaseException("Access denied", HttpStatus.FORBIDDEN);
    }

}
