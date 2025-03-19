package com.backend.shop.domains.usecase;

import java.util.List;
import java.util.Optional;

import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.domains.models.Category;

public interface ICategoryusecase {

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Long id);

    Category getByName(String name);

    List<Category> getAllCategory(DataTableFilter dataTableFilter);

    Long countCategory();

    Optional<Category> getCategoryById(Long id);

    Optional<Category> getByParentId(Long id);

}
