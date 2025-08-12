package com.backend.shop.applications.interfaces;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.backend.shop.applications.dto.roleAndPermission.PermissionsDTO;
import com.backend.shop.applications.dto.roleAndPermission.RoleBasicDTO;
import com.backend.shop.applications.dto.roleAndPermission.RoleDTO;

public interface IRoleAndPermissionService {
    void createRoleAndPermission(RoleDTO dto);
    List<RoleDTO> getRoleByName(String name);
    List<RoleDTO> getAllRoleWithRoleLevel();
    List<PermissionsDTO> getAllPermission();
    DataTablesOutput<RoleDTO> dataTable(DataTablesInput input);
    DataTablesOutput<RoleBasicDTO> dataTableBasic(DataTablesInput input);
}
