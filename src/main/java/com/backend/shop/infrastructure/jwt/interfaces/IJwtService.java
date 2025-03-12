package com.backend.shop.infrastructure.jwt.interfaces;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface IJwtService {
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(String username);
    String generateToken(Map<String,?> extractClaims,String username,long expirationTime);
    boolean isTokenValid(String token , UserDetails userDetails);
    boolean isTokenExpired(String token);
    Date extraExpired(String token);
    Claims extractAllClaims(String token);
    SecretKey getSignInKey();
    String generateRefreshToken(String username);

}
