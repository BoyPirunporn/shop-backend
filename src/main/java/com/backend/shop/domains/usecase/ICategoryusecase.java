package com.backend.shop.domains.usecase;

import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.domains.models.Category;

import java.util.List;

public interface ICategoryusecase {
    void createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Long id);
    Category getByName(String name);
    List<Category> getAllCategory(DataTableFilter dataTableFilter);
    Long countCategory();

}
