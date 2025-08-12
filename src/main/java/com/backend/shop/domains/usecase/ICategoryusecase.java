package com.backend.shop.domains.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.domains.models.Category;

public interface ICategoryuseCase {

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Long id);

    Category getByName(String name);

    Page<Category> getAllCategory(DataTableFilter dataTableFilter);
    DataTablesOutput<Category> getAllCategory(DataTablesInput dataTableFilter);

    Long countCategory();

    Optional<Category> getCategoryById(Long id);

    Optional<Category> getByParentId(Long id);
    List<Category> getAllCategoryByParentParentIsNull();
    List<Category> getAllCategoryByParentIsNull();

}
