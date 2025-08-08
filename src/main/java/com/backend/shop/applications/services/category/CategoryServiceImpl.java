package com.backend.shop.applications.services.category;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.applications.dto.category.request.CategoryRequest;
import com.backend.shop.applications.interfaces.ICategoryService;
import com.backend.shop.applications.interfaces.IFileService;
import com.backend.shop.applications.mapper.CategoryModelMapper;
import com.backend.shop.domains.ResponseWithPayload;
import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.domains.datatable.ResponseDataTable;
import com.backend.shop.domains.models.Category;
import com.backend.shop.domains.usecase.ICategoryusecase;
import com.backend.shop.infrastructure.exceptions.BaseException;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final ICategoryusecase categoryUsecase;
    private final CategoryModelMapper categoryMapper;
    private final IFileService fileService;

    public CategoryServiceImpl(ICategoryusecase categoryUsecase, CategoryModelMapper categoryMapper,
            IFileService fileService) {
        this.categoryUsecase = categoryUsecase;
        this.categoryMapper = categoryMapper;
        this.fileService = fileService;
    }

    @Override
    public ResponseDataTable<CategoryDTO> getAllCategory(DataTableFilter filter) {
        Page<Category> pageCategories = categoryUsecase.getAllCategory(filter);
        return new ResponseDataTable<>(200, pageCategories.map(categoryMapper::toDTO).toList(),
                (long) pageCategories.getTotalElements(), filter.getPage(), filter.getSize());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryMapper.toDTO(categoryUsecase.getCategoryById(id)
                .orElseThrow(() -> new BaseException("Data not found", HttpStatus.BAD_REQUEST)));
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        categoryUsecase.updateCategory(categoryMapper.toModel(categoryDTO));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryUsecase.deleteCategory(id);
    }

    @Override
    public void createCategory(CategoryRequest req) throws IOException {
        Category model = mapToCategory(req, null);
        categoryUsecase.createCategory(model);

    }

    @Override
    public void updateCategory(CategoryRequest category) throws IOException {
        try {
            // Category cModel = null;
            // if (category.getParentId() != null) {
            // cModel =
            // categoryUsecase.getCategoryById(category.getParentId()).orElseThrow(() -> new
            // BaseException("Parent not found", HttpStatus.BAD_REQUEST));
            // }
            Category model = mapToCategory(category, null);
            categoryUsecase.updateCategory(model);
        } catch (BaseException ex) {
            throw new BaseException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * แปลง CategoryRequest เป็น Category
     */
    private Category mapToCategory(CategoryRequest req, Category parent) throws IOException {
        Category cate = new Category();
        cate.setId(req.getId());
        cate.setName(req.getName());
        cate.setParent(parent);
        if (req.getImageUrl() != null && req.getImageUrl() instanceof MultipartFile) {
            String saveFile = uploadImage((MultipartFile) req.getImageUrl());
            cate.setImageUrl(saveFile);
        } else {
            if (req.getImageUrl() != null) {
                logger.debug("REQUEST IS NOT NULL : " + req.getImageUrl());
                cate.setImageUrl((String) req.getImageUrl());
            } else if (parent != null) {
                logger.debug("PARENT IS NOT NULL : " + parent.getImageUrl());
                cate.setImageUrl(parent.getImageUrl());
            } else {
                logger.debug("BOTH IS NULL");
                cate.setImageUrl(null);
            }
        }
        // ตรวจสอบ children และ map พวกมัน
        List<Category> children = Optional.ofNullable(req.getChildren())
                .orElse(Collections.emptyList()) // ป้องกัน NPE
                .stream()
                .map(childReq -> {
                    try {
                        return mapToCategory(childReq, cate); // Recursion พร้อมส่ง parent ที่ถูกต้อง
                    } catch (IOException e) {
                        throw new RuntimeException("Error processing category child", e);
                    }
                })
                .collect(Collectors.toList());

        cate.setChildren(children); // กำหนด children ให้ category

        return cate;
    }

    /**
     * อัปโหลดรูปภาพและคืนค่า URL
     */
    private String uploadImage(MultipartFile file) throws IOException {
        UUID uid = UUID.randomUUID();
        String savePath = Paths.get("category", uid.toString(), file.getOriginalFilename()).toString();
        return fileService.createPath(file, savePath);
    }

    @Override
    public ResponseWithPayload<List<CategoryDTO>> getAllCategoryWithPayload(DataTableFilter filter) {
        return new ResponseWithPayload<>(200, categoryUsecase.getAllCategoryByParentIsNull().stream()
                .map(categoryMapper::toDTO).collect(Collectors.toList()));
    }

    @Override
    public DataTablesOutput<CategoryDTO> findAll(DataTablesInput input) {
        DataTablesOutput<Category> output = categoryUsecase.getAllCategory(input);

        DataTablesOutput<CategoryDTO> result = new DataTablesOutput<>();
        result.setRecordsFiltered(output.getRecordsFiltered());
        result.setRecordsTotal(output.getRecordsTotal());
        result.setError(output.getError());

        // map entities to models
        result.setData(
                output.getData()
                        .stream()
                        .map(categoryMapper::toDTO) // <- แปลงแต่ละ entity ไป model
                        .toList());
        return result;
    }
}
