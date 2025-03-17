package com.backend.shop.applications.services.category;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.shop.applications.dto.category.CategoryDTO;
import com.backend.shop.applications.dto.category.request.CategoryRequest;
import com.backend.shop.applications.interfaces.ICategoryService;
import com.backend.shop.applications.interfaces.IFileService;
import com.backend.shop.applications.mapper.CategoryModelMapper;
import com.backend.shop.domains.models.Category;
import com.backend.shop.domains.usecase.ICategoryusecase;

@Service
public class CategoryServiceImpl implements ICategoryService {

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
    public void createCategory(CategoryRequest req) throws IOException{
        Category model = new Category();
        model.setName(req.getName());
        if (req.getImageUrl() != null && req.getImageUrl() instanceof MultipartFile) {
            String saveFile = uploadImage((MultipartFile) req.getImageUrl());
            System.out.println("SAVE PATH MAIN : " + saveFile);
            model.setImageUrl(saveFile);
        }
        List<Category> children = Optional.ofNullable(req.getChildren())
                .orElse(Collections.emptyList()) // ป้องกัน NullPointerException
                .stream()
                .map(t -> {
                    try {
                        return mapToCategory(t,model);
                    } catch (IOException e) {
                        throw new RuntimeException("Error processing category child", e);
                    }
                }) // ใช้เมธอดช่วย
                .collect(Collectors.toList());
        model.setChildren(children);
        categoryUsecase.createCategory(model);

    }

    @Override
    public List<CategoryDTO> getAllCategory(int page, int size) {
        return categoryUsecase.getAllCategory(page, size).stream().map(categoryMapper::toDTO).toList();
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        categoryUsecase.updateCategory(categoryMapper.toModel(categoryDTO));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryUsecase.deleteCategory(id);
    }

    /**
     * แปลง CategoryRequest เป็น CategoryDTO
     */
    private Category mapToCategory(CategoryRequest req,Category model) throws IOException {
        Category cate = new Category();
        cate.setName(req.getName());
        cate.setParent(model);

        if (req.getImageUrl() instanceof MultipartFile) {
            String saveFile = uploadImage((MultipartFile) req.getImageUrl());
            System.out.println("SAVE PATH MAIN : " + saveFile);
            cate.setImageUrl(saveFile);
        }
        return cate;
    }

    /**
     * อัปโหลดรูปภาพและคืนค่า URL
     */
    private String uploadImage(MultipartFile file) throws IOException {
        UUID uid = UUID.randomUUID();
        String savePath = Paths.get("category", uid.toString(), file.getOriginalFilename()).toString();
        System.out.println("SAVE PATH " + savePath);
        return fileService.createPath(file, savePath);
    }
}
