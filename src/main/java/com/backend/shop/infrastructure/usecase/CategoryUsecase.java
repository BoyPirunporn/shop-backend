package com.backend.shop.infrastructure.usecase;

import java.util.List;

import com.backend.shop.domains.datatable.DataTableFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.Category;
import com.backend.shop.domains.usecase.ICategoryusecase;
import com.backend.shop.infrastructure.entity.CategoryEntity;
import com.backend.shop.infrastructure.mapper.CategoryEntityMapper;
import com.backend.shop.infrastructure.repository.CategoryJpaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryUsecase implements ICategoryusecase {

    private final CategoryJpaRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    public CategoryUsecase(CategoryJpaRepository categoryRepository, CategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    @Transactional
    public void createCategory(Category category) {
        // List<Category> children = Optional.ofNullable(category.getChildren())
       
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);

       if(!category.getChildren().isEmpty()){
           System.out.println("SUB CATE : "+categoryEntity.getChildren().get(0).getName());
           for (CategoryEntity c : categoryEntity.getChildren()) {
               c.setParent(categoryEntity);
           }
       }
        categoryRepository.save(categoryEntity);
    }



    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getByName(String name) {
        return null;
    }

    @Override
    public List<Category> getAllCategory(DataTableFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        return categoryRepository.findAll(pageable).stream().map(categoryEntityMapper::toModel).toList();
    }

    @Override
    public Long countCategory() {
        return categoryRepository.count();
    }
}
