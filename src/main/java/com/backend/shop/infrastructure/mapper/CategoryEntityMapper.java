package com.backend.shop.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.Category;
import com.backend.shop.infrastructure.entity.CategoryEntity;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface CategoryEntityMapper {

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "parent",ignore = true)
    Category toModel(CategoryEntity categoryEntity);


    @Mapping(target = "products", ignore = true)
    @Mapping(target = "parent",ignore = true)
    CategoryEntity toEntity(Category category);

}
