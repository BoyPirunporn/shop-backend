package com.backend.shop.presentation.controllers.menu;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.Views;
import com.backend.shop.applications.dto.menu.MenuItemDTO;
import com.backend.shop.applications.interfaces.IMenuService;
import com.backend.shop.domains.ResponseMessage;
import com.backend.shop.domains.ResponseWithPayload;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    private final IMenuService menuService;

    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    @JsonView(Views.MenuItem.class)
    public ResponseEntity<ResponseWithPayload<List<MenuItemDTO>>> getMenu() {
        return ResponseEntity.ok(menuService.getMenuByRole());
    }
    @GetMapping("/all")
    @JsonView(Views.MenuItemWithOutRoleMenuPermission.class)
    public ResponseEntity<ResponseWithPayload<List<MenuItemDTO>>> getAllMenu() {
        return ResponseEntity.ok(menuService.getAllMenu());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> created(@RequestBody MenuItemDTO dto){
        System.out.println(dto);
        menuService.created(dto);
        return ResponseEntity.ok(new ResponseMessage(201,"Menu has been created."));
    }

    @PostMapping("/datatable")
    @JsonView(Views.MenuItemDatatable.class)
    public ResponseEntity<DataTablesOutput<MenuItemDTO>> getMenu(@RequestBody DataTablesInput input) {
        return ResponseEntity.ok(menuService.getMenu(input));
    }

    @PostMapping("/find-by-title")
    public ResponseEntity<ResponseWithPayload<List<MenuItemDTO>>> getByTitle(@RequestBody Map<String, String> body) {
        if (!body.containsKey("value")) {
            throw new BaseException("Field 'value' is not define", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(menuService.getByTitle(body.get("value")));
    }

    @PostMapping("/datatable/{parentId}")
    public ResponseEntity<DataTablesOutput<MenuItemDTO>> getMenu(@RequestBody DataTablesInput input,
            @PathVariable(name = "parentId") String parentId) {
        return ResponseEntity.ok(menuService.getMenu(input, parentId));
    }
}
