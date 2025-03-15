package com.backend.shop.applications.interfaces;

import com.backend.shop.applications.dto.auth.AuthDTO;
import com.backend.shop.applications.dto.auth.SignInDTO;
import com.backend.shop.applications.dto.auth.TokenDTO;
import com.backend.shop.domains.models.User;

public interface IAuthService {
    public TokenDTO signUp(AuthDTO auth);
    public TokenDTO signIn(SignInDTO sign);
    public TokenDTO refreshToken(String refreshToken);

    public User getByEmail(String email);
}
