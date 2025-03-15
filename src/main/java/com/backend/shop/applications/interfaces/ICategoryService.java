package com.backend.shop.applications.interfaces;

import java.util.List;

import com.backend.shop.applications.dto.category.CategoryDTO;

public interface ICategoryService {
    void createCategory(CategoryDTO category);
    void updateCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    List<CategoryDTO> getAllCategory(int page,int size);
}
