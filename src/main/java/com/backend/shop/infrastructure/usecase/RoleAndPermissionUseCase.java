package com.backend.shop.infrastructure.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.Role;
import com.backend.shop.domains.models.RoleBasic;
import com.backend.shop.domains.models.RoleMenuPermission;
import com.backend.shop.domains.usecase.IRoleAndPermissionUseCase;
import com.backend.shop.infrastructure.entity.PermissionEntity;
import com.backend.shop.infrastructure.entity.RoleEntity;
import com.backend.shop.infrastructure.entity.RoleMenuPermissionEntity;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.backend.shop.infrastructure.mapper.AuthMapper;
import com.backend.shop.infrastructure.mapper.RoleMenuPermissionMapper;
import com.backend.shop.infrastructure.repository.PermissionJpaRepository;
import com.backend.shop.infrastructure.repository.RoleJpaRepository;
import com.backend.shop.infrastructure.repository.RoleMenuPermissionJpaRepository;
import com.backend.shop.infrastructure.specification.role.RoleSpecification;
import com.backend.shop.infrastructure.utility.SecurityUtils;

import jakarta.transaction.Transactional;

@Service
public class RoleAndPermissionUseCase implements IRoleAndPermissionUseCase {
    private static final Logger logger = LoggerFactory.getLogger(RoleAndPermissionUseCase.class);
    private final RoleJpaRepository roleJpaRepository;
    private final RoleMenuPermissionJpaRepository roleMenuPermissionJpaRepository;
    private final RoleMenuPermissionMapper roleMenuPermissionMapper;
    private final PermissionJpaRepository permissionJpaRepository;
    private final AuthMapper authMapper;

    

    public RoleAndPermissionUseCase(RoleJpaRepository roleJpaRepository,
            RoleMenuPermissionJpaRepository roleMenuPermissionJpaRepository,
            RoleMenuPermissionMapper roleMenuPermissionMapper, PermissionJpaRepository permissionJpaRepository,
            AuthMapper authMapper) {
        this.roleJpaRepository = roleJpaRepository;
        this.roleMenuPermissionJpaRepository = roleMenuPermissionJpaRepository;
        this.roleMenuPermissionMapper = roleMenuPermissionMapper;
        this.permissionJpaRepository = permissionJpaRepository;
        this.authMapper = authMapper;
    }

    @Override
    public void createRoleAndPermission(Role model) {
        RoleEntity entity = authMapper.toEntity(model);
        logger.info("ENTITY ROLE AND PERMISSION -> {}", entity.toString());
    }

    @Override
    @Transactional
    public void updatePermissionsForRole(Long roleId, List<RoleMenuPermission> permissions) {
        // 1. Validate that the role exists
        RoleEntity role = roleJpaRepository.findById(roleId)
                .orElseThrow(() -> new BaseException("Role not found with id: " + roleId, HttpStatus.NOT_FOUND));

        // 2. Remove all existing permissions for this role to ensure a clean state
        roleMenuPermissionJpaRepository.deleteByRoleId(roleId);

        // 3. If the new permission list is empty, we're done
        if (permissions == null || permissions.isEmpty()) {
            return;
        }

        // 4. Create and save the new permission entities
        List<RoleMenuPermissionEntity> newPermissions = permissions.stream()
                .map(model -> {
                    RoleMenuPermissionEntity entity = roleMenuPermissionMapper.toEntity(model);
                    entity.setRole(role); // Set the managed RoleEntity instance
                    return entity;
                }).collect(Collectors.toList());
        roleMenuPermissionJpaRepository.saveAll(newPermissions);
    }

    @Override
    public DataTablesOutput<Role> datatable(DataTablesInput input) {
        // Use a Specification to fetch all related data in one go, preventing N+1
        // problems.
        // This ensures that roleMenuPermissions and their nested menuItem/permission
        // are loaded.
        Specification<RoleEntity> spec = RoleSpecification.fetchFullRoleData(SecurityUtils.getCurrentRole());

        DataTablesOutput<RoleEntity> dataTable = roleJpaRepository.findAll(input, spec);

        DataTablesOutput<Role> result = new DataTablesOutput<>();
        result.setDraw(dataTable.getDraw());
        result.setRecordsFiltered(dataTable.getRecordsFiltered());
        result.setRecordsTotal(dataTable.getRecordsTotal());
        result.setError(dataTable.getError());
        result.setData(dataTable.getData().stream().map(authMapper::toModel).collect(Collectors.toList()));

        return result;
    }

    @Override
    public DataTablesOutput<RoleBasic> datatableBasic(DataTablesInput input) {

        DataTablesOutput<RoleEntity> dataTable = roleJpaRepository.findAll(input,
                (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("level"), SecurityUtils.getCurrentRole()));

        DataTablesOutput<RoleBasic> result = new DataTablesOutput<>();
        result.setDraw(dataTable.getDraw());
        result.setRecordsFiltered(dataTable.getRecordsFiltered());
        result.setRecordsTotal(dataTable.getRecordsTotal());
        result.setError(dataTable.getError());
        result.setData(dataTable.getData().stream().map(authMapper::toBasicModel).collect(Collectors.toList()));

        return result;
    }

    @Override
    public List<Role> findRoleByName(String name) {

        List<RoleEntity> entities = roleJpaRepository.findAllByNameStartingWithIgnoreCaseAndLevelLessThanEqual(name,
                SecurityUtils.getCurrentRole());

        return entities.stream().map(role -> {
            return new Role(role.getId(), role.getName(), role.getDescription(), role.getLevel());
        }).collect(Collectors.toList());

    }

    @Override
    public List<Role> findAllRoleWithRoleLevel() {
        List<RoleEntity> entities = roleJpaRepository.findAllByLevelGreaterThanEqual(SecurityUtils.getCurrentRole());

        return entities.stream().map(role -> {
            return new Role(role.getId(), role.getName(), role.getDescription(), role.getLevel());
        }).collect(Collectors.toList());

    }

    @Override
    public List<Permissions> findAllPermissions() {
        List<PermissionEntity> entities = permissionJpaRepository.findAll();
        return entities.stream().map(p -> {
            return new Permissions(p.getId(), p.getName(), p.getDescription());
        }).collect(Collectors.toList());
    }

}
