package com.backend.shop.applications.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.backend.shop.applications.dto.menu.MenuItemDTO;
import com.backend.shop.domains.models.MenuItem;
import com.backend.shop.domains.models.MenuItemBasic;

@Mapper(componentModel = "spring",uses = {RoleMenuPermissionModelMapper.class})
public interface MenuItemModelMapper {

    MenuItem toModel(MenuItemDTO dto);

    MenuItemDTO toDTO(MenuItem model);
    MenuItemDTO toDTO(MenuItemBasic model);
    
    @Named("toDTOIgnoreParentAndItem")
    @Mapping(target = "items",ignore = true)
    @Mapping(target = "parent",ignore = true)
    MenuItemDTO toDTOIgnoreParentAndItem(MenuItem model);



   
}
