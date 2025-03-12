package com.backend.shop.domains.usecase;

import com.backend.shop.domains.models.User;

public interface IAuthUsecase {
    public User createUser(User user);
    public boolean existingByEmail(String email);
    public User findByEmail(String email);
}
