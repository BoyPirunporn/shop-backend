package com.backend.shop.presentation.controllers.admin.category;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.applications.dto.category.request.CategoryRequest;
import com.backend.shop.applications.interfaces.ICategoryService;
import com.backend.shop.domains.ResponseMessage;
import com.backend.shop.domains.ResponseWithPayload;
import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.domains.datatable.ResponseDataTable;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Category Admin Controller")
@RequestMapping("${application.api.prefix}/admin/category")
public class CategoryAdminController {

    private final ICategoryService categoryService;

    public CategoryAdminController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseWithPayload<CategoryDTO>> getById(@PathVariable Long id){
        return ResponseEntity.ok(new ResponseWithPayload<>(200,categoryService.getCategoryById(id)));
    }
    @GetMapping
    public ResponseEntity<ResponseDataTable<CategoryDTO>> getAllCategory(@ModelAttribute DataTableFilter filter){
        return ResponseEntity.ok(categoryService.getAllCategory(filter));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ResponseMessage> createCategory(@ModelAttribute CategoryRequest category) throws IOException{
        categoryService.createCategory(category);
        ResponseMessage response = new ResponseMessage();
        response.setStatus(200);
        response.setMessage("Category has been created.");
        return ResponseEntity.ok(response);
    }

    @PutMapping(consumes = "multipart/form-data")
    public ResponseEntity<ResponseMessage> updateCategory(@ModelAttribute CategoryRequest category) throws IOException{
        categoryService.updateCategory(category);
        ResponseMessage response = new ResponseMessage();
        response.setStatus(200);
        response.setMessage("Category has been created.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        ResponseMessage response = new ResponseMessage();
        response.setStatus(200);
        response.setMessage("Category has been deleted.");
        return ResponseEntity.ok(response);
    }

}
