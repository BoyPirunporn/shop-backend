package com.backend.shop.applications.interfaces;

import com.backend.shop.applications.dto.user.UserRequest;

public interface IUserService {
    void createUser(UserRequest user);
    void updateUser(UserRequest user);
    void deleteUser(Long id);
}
