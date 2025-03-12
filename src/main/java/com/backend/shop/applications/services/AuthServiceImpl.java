package com.backend.shop.applications.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.auth.AuthDTO;
import com.backend.shop.applications.dto.auth.SignInDTO;
import com.backend.shop.applications.dto.auth.TokenDTO;
import com.backend.shop.applications.interfaces.IAuthService;
import com.backend.shop.domains.models.User;
import com.backend.shop.domains.usecase.IAuthUsecase;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.backend.shop.infrastructure.jwt.interfaces.IJwtService;

@Service
public class AuthServiceImpl implements IAuthService {

    private final IAuthUsecase authUsecase;
    private final IJwtService jwtService;
private final PasswordEncoder passwordEncoder;
    public AuthServiceImpl(IAuthUsecase authUsecase, IJwtService jwtService,PasswordEncoder passwordEncoder) {
        this.authUsecase = authUsecase;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signUp(AuthDTO auth) {
        System.out.println(auth.toString());
        User user = new User(auth.getEmail(), passwordEncoder.encode(auth.getPassword()), auth.getRoles());
        if (authUsecase.existingByEmail(auth.getEmail())) {
            throw new BaseException("Email already exists", HttpStatus.BAD_REQUEST);
        }
        User saveUser = authUsecase.createUser(user);
        String token = jwtService.generateToken(saveUser.getEmail());
        return token;

    }

    @Override
    public TokenDTO signIn(SignInDTO sign) {
        User user = authUsecase.findByEmail(sign.getEmail());
        System.out.println(user.getPassword());
        if (user == null || !passwordEncoder.matches(sign.getPassword(),user.getPassword())) {
            throw new BaseException("Email or password not found", HttpStatus.UNAUTHORIZED);
        }
        
        String token = jwtService.generateToken(user.getEmail());
        String refresnToken = jwtService.generateRefreshToken(user.getEmail());
        return new TokenDTO(token,refresnToken,user.getRoles());
    }

}
