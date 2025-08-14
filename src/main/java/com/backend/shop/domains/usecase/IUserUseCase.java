package com.backend.shop.domains.usecase;

import java.util.Set;

import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.RoleMenuPermission;
import com.backend.shop.domains.models.User;

public interface IUserUseCase {
    void createUser(User user,Set<RoleMenuPermission> roleMenuPermissions);
    Permissions findPermissionByName(String name);
}
