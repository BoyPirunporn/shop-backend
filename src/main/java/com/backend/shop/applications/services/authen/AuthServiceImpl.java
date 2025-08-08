package com.backend.shop.applications.services.authen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final IAuthUsecase authUsecase;
    private final IJwtService jwtService;
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(IAuthUsecase authUsecase, IJwtService jwtService, UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        this.authUsecase = authUsecase;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenDTO signUp(AuthDTO auth) {
        logger.debug(auth.toString());
        User user = new User(auth.getEmail(), passwordEncoder.encode(auth.getPassword()), auth.getRoles());
        if (authUsecase.existingByEmail(auth.getEmail())) {
            throw new BaseException("Email already exists", HttpStatus.BAD_REQUEST);
        }
        User saveUser = authUsecase.createUser(user);
        String token = jwtService.generateToken(saveUser.getEmail());
        String refresnToken = jwtService.generateRefreshToken(user.getEmail());
        return new TokenDTO(token, refresnToken, user.getRoles());

    }

    @Override
    public TokenDTO signIn(SignInDTO sign) {
        User user = authUsecase.findByEmail(sign.getEmail());
        logger.debug(user.getPassword());
        if (user == null || !passwordEncoder.matches(sign.getPassword(), user.getPassword())) {
            throw new BaseException("Email or password not found", HttpStatus.UNAUTHORIZED);
        }

        
        String token = jwtService.generateToken(user.getEmail());
        String refresnToken = jwtService.generateRefreshToken(user.getEmail());
        return new TokenDTO(token, refresnToken, user.getRoles());
    }

    @Override
    public TokenDTO refreshToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!jwtService.isTokenValid(refreshToken, userDetails)) {
            throw new BaseException("Invalid Refresh Token", HttpStatus.UNAUTHORIZED);
        }
        String newAccessToken = jwtService.generateToken(userDetails.getUsername());
        return new TokenDTO(newAccessToken, refreshToken);
    }

    @Override
    public User getByEmail(String email) {
       return  authUsecase.findByEmail(email);
    }

}
