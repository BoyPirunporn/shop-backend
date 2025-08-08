package com.backend.shop.applications.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.backend.shop.applications.dto.menu.MenuItemDTO;
import com.backend.shop.domains.models.MenuItem;

@Mapper(componentModel = "spring")
public interface MenuItemModelMapper {

    MenuItem toModel(MenuItemDTO dto);

    MenuItemDTO toDTO(MenuItem model);
    
    @Named("toDTOIgnoreParentAndItem")
    @Mapping(target = "items",ignore = true)
    @Mapping(target = "parent",ignore = true)
    MenuItemDTO toDTOIgnoreParentAndItem(MenuItem model);



   
}
