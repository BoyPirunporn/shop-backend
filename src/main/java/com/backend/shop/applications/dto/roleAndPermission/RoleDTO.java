package com.backend.shop.applications.dto.roleAndPermission;

import com.backend.shop.applications.dto.Views;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.HashSet;
import java.util.Set;

public class RoleDTO extends RoleBasicDTO{

   
    @JsonView({Views.DataTable.class})
    private Set<RoleMenuPermissionDTO> roleMenuPermissions = new HashSet<>();

    public Set<RoleMenuPermissionDTO> getRoleMenuPermissions() {
        return roleMenuPermissions;
    }

}
