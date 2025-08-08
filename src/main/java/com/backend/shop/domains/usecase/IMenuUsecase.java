package com.backend.shop.domains.usecase;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.backend.shop.domains.models.MenuItem;

public interface IMenuUsecase {
    List<MenuItem> getMenu();
    List<MenuItem> getMenuByTitle(String title);
    void created(MenuItem menuItem);

    DataTablesOutput<MenuItem> getMenuDataTable(DataTablesInput input);
    DataTablesOutput<MenuItem> getMenuDataTable(DataTablesInput input,String parentId);

}
