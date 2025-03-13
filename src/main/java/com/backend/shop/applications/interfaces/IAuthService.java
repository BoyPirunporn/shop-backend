package com.backend.shop.applications.interfaces;

import com.backend.shop.applications.dto.auth.AuthDTO;
import com.backend.shop.applications.dto.auth.SignInDTO;
import com.backend.shop.applications.dto.auth.TokenDTO;

public interface IAuthService {
    public TokenDTO signUp(AuthDTO auth);
    public TokenDTO signIn(SignInDTO sign);
}
