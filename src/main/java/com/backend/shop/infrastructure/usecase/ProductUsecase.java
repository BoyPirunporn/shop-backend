package com.backend.shop.infrastructure.usecase;

import java.util.List;
import java.util.Optional;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.infrastructure.entity.*;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.backend.shop.infrastructure.mapper.ProductEntityMapper;
import com.backend.shop.infrastructure.repository.CategoryJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.Product;
import com.backend.shop.domains.usecase.IProductUsecase;
import com.backend.shop.infrastructure.repository.ProductJpaRepository;

@Service
public class ProductUsecase implements IProductUsecase {

    private final ProductJpaRepository productJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;

    private final ProductEntityMapper productEntityMapper;

    public ProductUsecase(ProductJpaRepository productJpaRepository, CategoryJpaRepository categoryJpaRepository, ProductEntityMapper productEntityMapper) {
        this.productJpaRepository = productJpaRepository;
        this.categoryJpaRepository = categoryJpaRepository;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    @Transactional
    public void createProduct(Product product) {
        CategoryEntity category = categoryJpaRepository.findByNameContainingIgnoreCase(product.getCategory().getName())
                .orElseThrow(() -> new BaseException("Category not found", HttpStatus.BAD_REQUEST));


        ProductEntity _product = productEntityMapper.toEntity(product);
        _product.setCategory(category);

        // ✅ ผูก Variant กับ Product และ Option กับ Variant
        for (ProductVariantEntity variant : _product.getProductVariants()) {
            variant.setProduct(_product); // 🟢 กำหนด product ให้ variant
            if (variant.getVariantImage() != null) { // ✅ ตรวจสอบก่อนใช้
                variant.getVariantImage().setProductVariant(variant);
            }
            for (ProductVariantOptionEntity variantOption : variant.getProductVariantOptions()) {
                variantOption.setProductVariant(variant); // 🟢 กำหนด variant ให้ variantOption
            }

        }
      productJpaRepository.save(_product);
    }



    @Override
    public void deleteProductById(Long id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts(int page,int size) {
       return productJpaRepository.findAll().stream().map(productEntityMapper::toModel).toList();
    }

    @Override
    public Product getProductByName(String name) {
        return productEntityMapper.toModel(productJpaRepository.findByNameContainingIgnoreCase(name).orElse(null));
    }

}
