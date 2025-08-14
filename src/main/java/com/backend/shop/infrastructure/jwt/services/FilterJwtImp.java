package com.backend.shop.infrastructure.jwt.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.backend.shop.infrastructure.config.SecurityConstant;
import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.jwt.exceptions.JwtException;
import com.backend.shop.infrastructure.jwt.interfaces.IFilterJwt;
import com.backend.shop.infrastructure.jwt.interfaces.IJwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class FilterJwtImp extends OncePerRequestFilter implements IFilterJwt {
    private static final Logger logger = LoggerFactory.getLogger(FilterJwtImp.class);

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    private final UserDetailsServiceImpl userDetailsService;

    private final IJwtService jwtService;

    public FilterJwtImp(
            UserDetailsServiceImpl userDetailsService,
            IJwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected boolean shouldNotFilter(@SuppressWarnings("null") HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // log.info("MATCH : " + EXCLUDED_URLS.stream().anyMatch(path::matches));
        return SecurityConstant.PUBLIC_ROUTE.stream()
                .anyMatch(url -> url.endsWith("/*") ? path.startsWith(url.replace("/*", "")) : path.equals(url));
    }

    @Override
    protected void doFilterInternal(
            @SuppressWarnings("null") HttpServletRequest request,
            @SuppressWarnings("null") HttpServletResponse response,
            @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {

                logger.info("[ REQUEST URI ] {}",request.getRequestURI());
        if (shouldNotFilter(request)) {
            logger.info("SHOULD NOT FILTER");
            filterChain.doFilter(request, response);
            return;
        }
        logger.info("SHOULD FILTER");

        // log.info("URI " + uri);
        String headerToken = request.getHeader("Authorization");
        logger.info("headerToken -> {}",headerToken);
        if (isTokenInvalid(headerToken)) {
            resolver.resolveException(request, response, null,
                    new JwtException("Unauthorized", HttpStatus.UNAUTHORIZED));
            return;
        }

        logger.info("Check token");
        try {
            final String token = extractToken(headerToken);
            logger.info("EXTRACT TOKEN");
            String username = jwtService.extractUsername(token);
            logger.info("EXTRACT USERNAME");
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.info("HANDLE AUTHENTICATION ");
                handleValidToken(username, token, request);
            }
            logger.info("Allow Access Token -> {}",username);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.info(e.getMessage());
            resolver.resolveException(request, response, null,
                    new JwtException(e.getMessage(), HttpStatus.UNAUTHORIZED));
            return;
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
        logger.info("GET USER DETAIL");
        UsersEntity userDetails = (UsersEntity) userDetailsService.loadUserByUsername(username);
        logger.info("USER DETAIL IS -> {}",userDetails.getEmail());
        if (jwtService.isTokenValid(token, userDetails)) {
            logger.info("Token is valid -> {}",userDetails.getEmail());
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            logger.info("Initial User Token -> {}",userDetails.getEmail());
            userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            logger.info("Set User Token -> {}",userDetails.getEmail());
            SecurityContextHolder.getContext().setAuthentication(userToken);
            logger.info("Set SecurityContext -> {}",userDetails.getEmail());
        }
    }

    @Override
    public void handleInvalidToken(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleInvalidToken'");
    }

}
