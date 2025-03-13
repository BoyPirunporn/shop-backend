package com.backend.shop.applications.mapper;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.domains.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryModelMapper {

    Category toModel(CategoryDTO dto);
    CategoryDTO toDTO(Category model);
}
