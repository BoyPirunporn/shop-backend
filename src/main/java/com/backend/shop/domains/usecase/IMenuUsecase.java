package com.backend.shop.domains.usecase;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.backend.shop.domains.models.MenuItem;
import com.backend.shop.domains.models.MenuItemBasic;

public interface IMenuUseCase {
    List<MenuItem> getAllMenu();
    List<MenuItem> getMenuByRole();
    List<MenuItem> getMenuByTitle(String title);
    void created(MenuItem menuItem);

    DataTablesOutput<MenuItem> getMenuDataTable(DataTablesInput input);
    DataTablesOutput<MenuItemBasic> getMenuDataTableBasic(DataTablesInput input);
    DataTablesOutput<MenuItem> getMenuDataTable(DataTablesInput input,String parentId);

}
