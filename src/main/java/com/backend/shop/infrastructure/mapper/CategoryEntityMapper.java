package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.backend.shop.domains.models.Category;
import com.backend.shop.infrastructure.entity.CategoryEntity;

@Mapper(componentModel = "spring" )
public interface CategoryEntityMapper {

    @Mapping(target = "products", ignore = true)
    @Mapping(source="parent",target = "parent",qualifiedByName = "toParentModel")
    Category toModel(CategoryEntity categoryEntity);


    @Mapping(target = "products", ignore = true)
    @Mapping(source="parent",target = "parent",qualifiedByName = "toParentEntity")
    CategoryEntity toEntity(Category category);

    @Named("toParentEntity")
    default CategoryEntity toParentEntity(Category parent){
        if(parent == null) return null;
        return new CategoryEntity(parent.getId(),parent.getName(),parent.getImageUrl());
    }
    @Named("toParentModel")
    default Category toParentModel(CategoryEntity parent){
        if(parent == null) return null;
        return new Category(parent.getId(),parent.getName(),parent.getImageUrl());
    }

    
}
