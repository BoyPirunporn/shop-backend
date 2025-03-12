package com.backend.shop.infrastructure.jwt.exceptions;

import org.springframework.http.HttpStatus;

public class JwtException extends RuntimeException {
    private HttpStatus status;

   

    public JwtException(String message) {
        super(message);
    }

    public JwtException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
