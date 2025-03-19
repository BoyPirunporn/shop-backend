package com.backend.shop.infrastructure.usecase;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.backend.shop.applications.dto.category.request.CategoryRequest;
import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.infrastructure.exceptions.BaseException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.Category;
import com.backend.shop.domains.usecase.ICategoryusecase;
import com.backend.shop.infrastructure.entity.CategoryEntity;
import com.backend.shop.infrastructure.mapper.CategoryEntityMapper;
import com.backend.shop.infrastructure.repository.CategoryJpaRepository;

import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        CategoryEntity categoryEntity = mapToCategoryEntity(category,null);
        categoryRepository.save(categoryEntity);
    }

    private CategoryEntity mapToCategoryEntity(Category model, CategoryEntity parent) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setParent(parent);
        entity.setImageUrl(
                (model.getImageUrl() != null) ? model.getImageUrl() :
                        (parent != null ? parent.getImageUrl() : null)
        );
        // ตรวจสอบ children และ map พวกมัน
        List<CategoryEntity> children = Optional.ofNullable(model.getChildren())
                .orElse(Collections.emptyList()) // ป้องกัน NPE
                .stream()
                .map(child -> {
                    return mapToCategoryEntity(child, entity);
                })
                .collect(Collectors.toList());
        entity.setChildren(children); // กำหนด children ให้ category


        return entity;
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

    @Override
    public Category getCategoryById(Long id) {
        return categoryEntityMapper.toModel(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public Optional<Category> getByParentId(Long id) {
        return categoryRepository.findFirstByParentId(id).map(categoryEntityMapper::toModel);
    }
}
