package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.Category;
import com.backend.shop.infrastructure.entity.CategoryEntity;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})
public interface CategoryMapper {
    Category toModel(CategoryEntity categoryEntity);
    CategoryEntity toEntity(Category category);
}
