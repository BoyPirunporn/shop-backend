package com.backend.shop.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.auth.AuthDTO;
import com.backend.shop.applications.dto.auth.SignInDTO;
import com.backend.shop.applications.dto.auth.TokenDTO;
import com.backend.shop.applications.interfaces.IAuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("${api.prefix}/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @PostMapping(path = "/sign-up", consumes = "application/json")
    public ResponseEntity<String> signUp(@Valid @RequestBody AuthDTO signUpReq) {
        return ResponseEntity.ok(authService.signUp(signUpReq));
    }
    @PostMapping(path = "/sign-in", consumes = "application/json")
    public ResponseEntity<TokenDTO> signIn(@Valid @RequestBody SignInDTO signIn) {
        return ResponseEntity.ok(authService.signIn(signIn));
    }

}
