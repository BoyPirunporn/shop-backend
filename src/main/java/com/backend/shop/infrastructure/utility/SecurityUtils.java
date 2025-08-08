package com.backend.shop.infrastructure.utility;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtils {

    public static String getCurrentUser() {
        Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();

        if (authenticator != null && authenticator.getPrincipal() instanceof UserDetails) {
            try {
                log.info(new ObjectMapper().writeValueAsString(authenticator.getPrincipal()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ((UserDetails) authenticator.getPrincipal()).getUsername();
        }
        throw new BaseException("Unauthorization", HttpStatus.UNAUTHORIZED);
    }

    public static UsersEntity getCurrentUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("INSTANCEOF  -> " + (authentication.getPrincipal() instanceof UsersEntity));
        if (authentication != null && authentication.getPrincipal() instanceof UsersEntity) {
            
            return (UsersEntity) authentication.getPrincipal();
        }
        return null;
    }
}
