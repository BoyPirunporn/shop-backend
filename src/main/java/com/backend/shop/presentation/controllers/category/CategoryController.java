package com.backend.shop.presentation.controllers.category;

import java.util.ArrayList;
import java.util.List;

import com.backend.shop.domains.datatable.DataTableFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.applications.interfaces.ICategoryService;
import com.backend.shop.domains.ResponseWithPayload;
import com.backend.shop.domains.datatable.FilterCategory;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("${application.api.prefix}/category")
@Tag(name = "Category Controller")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<ResponseWithPayload<List<CategoryDTO>>> filterCategory(@ModelAttribute DataTableFilter filter) {
        return ResponseEntity.ok(new ResponseWithPayload<>(200,new ArrayList<>()));
    }
}
