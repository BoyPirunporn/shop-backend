package com.backend.shop.applications.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.menu.MenuItemDTO;
import com.backend.shop.applications.interfaces.IMenuService;
import com.backend.shop.applications.mapper.MenuItemModelMapper;
import com.backend.shop.domains.ResponseWithPayload;
import com.backend.shop.domains.models.MenuItem;
import com.backend.shop.domains.usecase.IMenuUsecase;

@Service
public class MenuService implements IMenuService {

    private final MenuItemModelMapper menuItemModelMapper;
    private final IMenuUsecase menuUsecase;

    public MenuService(MenuItemModelMapper menuItemModelMapper, IMenuUsecase menuUsecase) {
        this.menuItemModelMapper = menuItemModelMapper;
        this.menuUsecase = menuUsecase;
    }

    @Override
    public ResponseWithPayload<List<MenuItemDTO>> getMenu() {
        List<MenuItemDTO> dtos = menuUsecase.getMenu().stream().map(menuItemModelMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseWithPayload<>(200, dtos);
    }

    @Override
    public DataTablesOutput<MenuItemDTO> getMenu(DataTablesInput input) {
       DataTablesOutput<MenuItem> output = menuUsecase.getMenuDataTable(input);

        DataTablesOutput<MenuItemDTO> result = new DataTablesOutput<>();
        result.setRecordsFiltered(output.getRecordsFiltered());
        result.setRecordsTotal(output.getRecordsTotal());
        result.setError(output.getError());

        // map entities to models
        result.setData(
                output.getData()
                        .stream()
                        .map(menuItemModelMapper::toDTO) // <- แปลงแต่ละ entity ไป model
                        .toList());
        return result;
    }
    @Override
    public DataTablesOutput<MenuItemDTO> getMenu(DataTablesInput input,String parentId) {
       DataTablesOutput<MenuItem> output = menuUsecase.getMenuDataTable(input,parentId);

        DataTablesOutput<MenuItemDTO> result = new DataTablesOutput<>();
        result.setRecordsFiltered(output.getRecordsFiltered());
        result.setRecordsTotal(output.getRecordsTotal());
        result.setError(output.getError());

        // map entities to models
        result.setData(
                output.getData()
                        .stream()
                        .map(menuItemModelMapper::toDTO) // <- แปลงแต่ละ entity ไป model
                        .toList());
        return result;
    }


    @Override
    public ResponseWithPayload<List<MenuItemDTO>> getByTitle(String title){
        return new ResponseWithPayload<>(200,menuUsecase.getMenuByTitle(title).stream().map(menuItemModelMapper::toDTOIgnoreParentAndItem).collect(Collectors.toList()));
    }
    
    @Override
    public void created(MenuItemDTO dto){
        menuUsecase.created(menuItemModelMapper.toModel(dto));
    }
}
