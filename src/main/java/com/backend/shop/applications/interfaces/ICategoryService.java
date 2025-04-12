package com.backend.shop.applications.interfaces;

import java.io.IOException;
import java.util.List;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.applications.dto.category.request.CategoryRequest;
import com.backend.shop.domains.ResponseWithPayload;
import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.domains.datatable.ResponseDataTable;

public interface ICategoryService {
    void createCategory(CategoryRequest category) throws IOException;
    void updateCategory(CategoryRequest category) throws IOException;
    void updateCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    ResponseDataTable<CategoryDTO> getAllCategory(DataTableFilter filter);
    ResponseWithPayload<List<CategoryDTO>> getAllCategoryWithPayload(DataTableFilter filter);
    CategoryDTO getCategoryById(Long id);
}
