package com.backend.shop.applications.services.roleAndPermission;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.roleAndPermission.PermissionsDTO;
import com.backend.shop.applications.dto.roleAndPermission.RoleBasicDTO;
import com.backend.shop.applications.dto.roleAndPermission.RoleDTO;
import com.backend.shop.applications.interfaces.IRoleAndPermissionService;
import com.backend.shop.applications.mapper.UserModelMapper;
import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.Role;
import com.backend.shop.domains.models.RoleBasic;
import com.backend.shop.infrastructure.usecase.RoleAndPermissionUseCase;

@Service
public class RoleAndPermissionServiceImpl implements IRoleAndPermissionService {

    private static final Logger logger = LoggerFactory.getLogger(RoleAndPermissionServiceImpl.class);
    private final RoleAndPermissionUseCase roleAndPermissionUseCase;
    private final UserModelMapper userModelMapper;


    public RoleAndPermissionServiceImpl(RoleAndPermissionUseCase roleAndPermissionUseCase,
            UserModelMapper userModelMapper) {
        this.roleAndPermissionUseCase = roleAndPermissionUseCase;
        this.userModelMapper = userModelMapper;
    }


    @Override
    public void createRoleAndPermission(RoleDTO dto) {
        logger.info("CREATE ROLE DTO  -> {}", dto.toString());
    }

    @Override
    public DataTablesOutput<RoleDTO> dataTable(DataTablesInput input) {
        DataTablesOutput<Role> dataTable = roleAndPermissionUseCase.datatable(input);

        DataTablesOutput<RoleDTO> result = new DataTablesOutput<>();
        result.setDraw(dataTable.getDraw());
        result.setRecordsFiltered(dataTable.getRecordsFiltered());
        result.setRecordsTotal(dataTable.getRecordsTotal());
        result.setError(dataTable.getError());
        result.setData(dataTable.getData().stream().map(userModelMapper::toDTO).collect(Collectors.toList()));

        return result;
    }
    @Override
    public DataTablesOutput<RoleBasicDTO> dataTableBasic(DataTablesInput input) {
        DataTablesOutput<RoleBasic> dataTable = roleAndPermissionUseCase.datatableBasic(input);

        DataTablesOutput<RoleBasicDTO> result = new DataTablesOutput<>();
        result.setDraw(dataTable.getDraw());
        result.setRecordsFiltered(dataTable.getRecordsFiltered());
        result.setRecordsTotal(dataTable.getRecordsTotal());
        result.setError(dataTable.getError());
        result.setData(dataTable.getData().stream().map(userModelMapper::toBasicDTO).collect(Collectors.toList()));

        return result;
    }

    @Override
    public List<RoleDTO> getRoleByName(String name) {
        List<Role> roles = roleAndPermissionUseCase.findRoleByName(name);
        return roles.stream().map(userModelMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public List<RoleDTO> getAllRoleWithRoleLevel() {
        List<Role> roles = roleAndPermissionUseCase.findAllRoleWithRoleLevel();
        return roles.stream().map(userModelMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public List<PermissionsDTO> getAllPermission() {
         List<Permissions> permissions = roleAndPermissionUseCase.findAllPermissions();
        return permissions.stream().map(userModelMapper::toDTO).collect(Collectors.toList());
    }
   

}
