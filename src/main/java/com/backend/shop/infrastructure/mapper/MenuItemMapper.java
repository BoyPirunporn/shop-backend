package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.backend.shop.domains.models.MenuItem;
import com.backend.shop.domains.models.MenuItemBasic;
import com.backend.shop.infrastructure.entity.MenuItemEntity;

@Mapper(componentModel = "spring",uses = {RoleMenuPermissionMapper.class})
public interface MenuItemMapper {   

    @Mapping(source="parent",target = "parent",qualifiedByName = "toMenuParentModel")
    MenuItem toModel(MenuItemEntity entity); 

    @Named("toModelWithOutRoleMenuPermission")
    @Mapping(source="parent",target = "parent",qualifiedByName = "toMenuParentModel")
    @Mapping(source="roleMenuPermissions",target = "roleMenuPermissions",ignore = true)
    MenuItem toModelWithOutRoleMenuPermission(MenuItemEntity entity); 
    
    @Mapping(source="parent",target = "parent",qualifiedByName = "toMenuParentModel")
    MenuItemBasic toModelBasic(MenuItemEntity entity); 
    
    @Mapping(source="parent",target = "parent",qualifiedByName = "toMenuParentEntity")
    MenuItemEntity toEntity(MenuItem model);
    
    @Named("toModelIgnoreParentAndItem")
    @Mapping(target = "items",ignore = true)
    @Mapping(target = "parent",ignore = true)
    MenuItem toModelIgnoreParentAndItem(MenuItemEntity model);

    @Named("toMenuParentEntity")
    default MenuItemEntity toMenuParentEntity(MenuItem parent){
        if(parent == null) return null;
        return new MenuItemEntity(parent.getId(),parent.getTitle(),parent.getUrl(),parent.getIcon(),parent.getSortOrder(),parent.getVisible(),parent.getIsGroup());
    }
    @Named("toMenuParentModel")
    default MenuItem toMenuParentModel(MenuItemEntity parent){
        if(parent == null) return null;
        return new MenuItem(parent.getId(),parent.getTitle(),parent.getUrl(),parent.getIcon(),parent.getSortOrder(),parent.getVisible(),parent.getIsGroup());
    }
}
