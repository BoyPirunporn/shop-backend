package com.backend.shop.presentation.controllers.auth;

import com.backend.shop.domains.ResponseWithPayload;
import org.antlr.v4.runtime.Token;
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
@RequestMapping("${application.api.prefix}/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/sign-up", consumes = "application/json")
    public ResponseEntity<ResponseWithPayload<TokenDTO>> signUp(@Valid @RequestBody AuthDTO signUpReq) {
        TokenDTO token = authService.signUp(signUpReq);
        ResponseWithPayload<TokenDTO> response = new ResponseWithPayload<>();
        response.setStatus(200);
        response.setPayload(token);
        return ResponseEntity.ok(response);
    }
    @PostMapping(path = "/sign-in", consumes = "application/json")
    public ResponseEntity<ResponseWithPayload<TokenDTO>> signIn(@Valid @RequestBody SignInDTO signIn) {
        TokenDTO token = authService.signIn(signIn);
        ResponseWithPayload<TokenDTO> response = new ResponseWithPayload<>();
        response.setStatus(200);
        response.setPayload(token);
        return ResponseEntity.ok(response);
    }

}
