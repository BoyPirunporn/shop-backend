package com.backend.shop.applications.interfaces;

import java.io.IOException;
import java.util.List;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.applications.dto.category.request.CategoryRequest;

public interface ICategoryService {
    void createCategory(CategoryRequest category) throws IOException;
    void updateCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    List<CategoryDTO> getAllCategory(int page,int size);
}
