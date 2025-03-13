package com.backend.shop.presentation.controllers.category;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.applications.interfaces.ICategoryService;
import com.backend.shop.domains.ResponseMessage;
import com.backend.shop.domains.ResponseWithPayload;
import com.backend.shop.domains.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.api.prefix}/category")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseWithPayload<CategoryDTO>> getById(@RequestParam Long id){
        return ResponseEntity.ok(new ResponseWithPayload<>(200,null));
    }
    @GetMapping
    public ResponseEntity<ResponseWithPayload<List<CategoryDTO>>> getAllCategory(@RequestParam(name = "page") int page, @RequestParam int size){
        ResponseWithPayload<List<CategoryDTO>> response = new ResponseWithPayload<>();
        response.setPayload(categoryService.getAllCategory(page,size));
        response.setStatus(200);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createCategory(@RequestBody CategoryDTO category){
        categoryService.createCategory(category);
        ResponseMessage response = new ResponseMessage();
        response.setStatus(200);
        response.setMessage("Category has been created.");
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> updateCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(categoryDTO);
        ResponseMessage response = new ResponseMessage();
        response.setStatus(200);
        response.setMessage("Category has been updated.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        ResponseMessage response = new ResponseMessage();
        response.setStatus(200);
        response.setMessage("Category has been deleted.");
        return ResponseEntity.ok(response);
    }

}
