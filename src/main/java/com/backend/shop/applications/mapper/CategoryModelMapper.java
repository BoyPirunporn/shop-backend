package com.backend.shop.applications.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.domains.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryModelMapper {

    
    Category toModel(CategoryDTO dto);

    CategoryDTO toDTO(Category model);

    List<CategoryDTO> toDtoList(List<Category> categories);
    List<Category> toModelList(List<CategoryDTO> categories);
}
