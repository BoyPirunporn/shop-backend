package com.backend.shop.applications.services.category;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.applications.interfaces.ICategoryService;
import com.backend.shop.applications.mapper.CategoryModelMapper;
import com.backend.shop.domains.usecase.ICategoryusecase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryusecase categoryUsecase;
    private final CategoryModelMapper categoryMapper;
    public CategoryServiceImpl(ICategoryusecase categoryUsecase, CategoryModelMapper categoryMapper){
        this.categoryUsecase = categoryUsecase;
        this.categoryMapper = categoryMapper;
    }
    @Override
    public void createCategory(CategoryDTO category) {
        categoryUsecase.createCategory(categoryMapper.toModel(category));
    }

    @Override
    public List<CategoryDTO> getAllCategory(int page,int size) {
        return categoryUsecase.getAllCategory(page,size).stream().map(categoryMapper::toDTO).toList();
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        categoryUsecase.updateCategory(categoryMapper.toModel(categoryDTO));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryUsecase.deleteCategory(id);
    }
}
