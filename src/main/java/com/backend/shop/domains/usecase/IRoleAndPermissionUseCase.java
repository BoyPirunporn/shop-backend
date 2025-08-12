package com.backend.shop.domains.usecase;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.Role;
import com.backend.shop.domains.models.RoleBasic;
import com.backend.shop.domains.models.RoleMenuPermission;

public interface IRoleAndPermissionUseCase {
    void createRoleAndPermission(Role model);

    void updatePermissionsForRole(Long roleId, List<RoleMenuPermission> permissions);

    List<Role> findRoleByName(String name);
    List<Role> findAllRoleWithRoleLevel();
    List<Permissions> findAllPermissions();

    DataTablesOutput<Role> datatable(DataTablesInput input);
    DataTablesOutput<RoleBasic> datatableBasic(DataTablesInput input);
}
