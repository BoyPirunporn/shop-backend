package com.backend.shop.presentation.controllers.admin.roleAndPermission;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.Views;
import com.backend.shop.applications.dto.roleAndPermission.PermissionsDTO;
import com.backend.shop.applications.dto.roleAndPermission.RoleBasicDTO;
import com.backend.shop.applications.dto.roleAndPermission.RoleDTO;
import com.backend.shop.applications.interfaces.IRoleAndPermissionService;
import com.backend.shop.domains.ResponseMessage;
import com.backend.shop.domains.ResponseWithPayload;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("${application.api.prefix}/role-permission")
public class RoleAndPermissionController {
    private final IRoleAndPermissionService roleAndPermissionService;

    public RoleAndPermissionController(IRoleAndPermissionService roleAndPermissionService) {
        this.roleAndPermissionService = roleAndPermissionService;
    }

    @PostMapping("/datatable")
    @JsonView({ Views.DataTable.class })
    public ResponseEntity<DataTablesOutput<RoleBasicDTO>> dataTable(@RequestBody DataTablesInput request) {
        return ResponseEntity.ok(roleAndPermissionService.dataTableBasic(request));
    }

    @PostMapping("/datatable-v2")
    public ResponseEntity<DataTablesOutput<RoleDTO>> dataTableV2(@RequestBody DataTablesInput request) {
        return ResponseEntity.ok(roleAndPermissionService.dataTable(request));
    }

    @GetMapping("/get-role-with-level")
    public ResponseEntity<ResponseWithPayload<List<RoleDTO>>> getAllRoles(){
        return ResponseEntity.ok(new ResponseWithPayload<>(200,roleAndPermissionService.getAllRoleWithRoleLevel()));
    }
    @GetMapping("/get-permissions")
    public ResponseEntity<ResponseWithPayload<List<PermissionsDTO>>> getAllPermissions(){
        return ResponseEntity.ok(new ResponseWithPayload<>(200,roleAndPermissionService.getAllPermission()));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> create(@RequestBody RoleDTO request) {
        return ResponseEntity.ok(new ResponseMessage(201, "Role has been created."));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> update(@RequestBody RoleDTO request) {
        return ResponseEntity.ok(new ResponseMessage(200, "Role has been updated."));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable(name = "id", required = true) Long id) {
        return ResponseEntity.ok(new ResponseMessage(200, "Role has been deleted."));
    }
}
