package com.backend.shop.infrastructure.jwt.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.backend.shop.infrastructure.jwt.exceptions.JwtException;
import com.backend.shop.infrastructure.jwt.interfaces.IFilterJwt;
import com.backend.shop.infrastructure.jwt.interfaces.IJwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class FilterJwtImp extends OncePerRequestFilter implements IFilterJwt {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    private final UserDetailsService userDetailsService;

    private final IJwtService jwtService;

    public FilterJwtImp(
            UserDetailsService userDetailsService,
            IJwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        System.out.println("URI "+uri);
        System.out.println(uri.startsWith("/files"));
        if (uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs") || uri.startsWith("/api/v1/auth") ||  uri.startsWith("/files")) {
            filterChain.doFilter(request, response);
            return;
        }
        String headerToken = request.getHeader("Authorization");

        if (isTokenInvalid(headerToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String token = extractToken(headerToken);

            String username = jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                handleValidToken(username, token, request);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            resolver.resolveException(request, response, null,
                    new JwtException(e.getMessage(), HttpStatus.UNAUTHORIZED));
        }
    }

    @Override
    public boolean isTokenInvalid(String token) {
        return token == null || !token.startsWith("Bearer");
    }

    @Override
    public String extractToken(String header) {
        return header.substring(7);
    }

    @Override
    public void handleValidToken(String username, String token, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(token, userDetails)) {
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(userToken);
        }
    }

    @Override
    public void handleInvalidToken(HttpServletRequest request, HttpServletResponse response) {
        resolver.resolveException(request, response, null, new JwtException("Invalid Token", HttpStatus.UNAUTHORIZED));
    }

}
