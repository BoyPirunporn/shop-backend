package com.backend.shop.infrastructure.jwt.interfaces;



import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IFilterJwt extends Filter {
    public boolean isTokenInvalid(String token);
    public String extractToken(String header);
    public void handleValidToken(String username, String token,HttpServletRequest request);
    public void handleInvalidToken(HttpServletRequest request,HttpServletResponse response);
}
