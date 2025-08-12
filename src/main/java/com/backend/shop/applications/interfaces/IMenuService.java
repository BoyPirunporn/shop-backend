package com.backend.shop.applications.interfaces;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.backend.shop.applications.dto.menu.MenuItemDTO;
import com.backend.shop.domains.ResponseWithPayload;

public interface IMenuService {
    ResponseWithPayload<List<MenuItemDTO>> getMenuByRole();
    ResponseWithPayload<List<MenuItemDTO>> getAllMenu();
    ResponseWithPayload<List<MenuItemDTO>> getByTitle(String title);
    DataTablesOutput<MenuItemDTO> getMenu(DataTablesInput input);
    DataTablesOutput<MenuItemDTO> getMenu(DataTablesInput input,String parentId);
    void created(MenuItemDTO dto);
}
