package com.backend.shop.applications.interfaces;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.domains.models.Category;

import java.util.List;

public interface ICategoryService {
    void createCategory(CategoryDTO category);
    void updateCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    List<CategoryDTO> getAllCategory(int page,int size);
}
