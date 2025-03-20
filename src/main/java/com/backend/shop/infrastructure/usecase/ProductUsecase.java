package com.backend.shop.infrastructure.usecase;

import java.util.List;

import com.backend.shop.infrastructure.entity.ProductOptionEntity;
import com.backend.shop.infrastructure.entity.ProductOptionValueEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.domains.models.Product;
import com.backend.shop.domains.usecase.IProductUsecase;
import com.backend.shop.infrastructure.entity.CategoryEntity;
import com.backend.shop.infrastructure.entity.ProductEntity;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.backend.shop.infrastructure.mapper.ProductEntityMapper;
import com.backend.shop.infrastructure.repository.CategoryJpaRepository;
import com.backend.shop.infrastructure.repository.ProductJpaRepository;
import com.backend.shop.infrastructure.specification.datatable.DataTableSpecification;

import jakarta.transaction.Transactional;

@Service
public class ProductUsecase implements IProductUsecase {

    private final ProductJpaRepository productJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;

    private final ProductEntityMapper productEntityMapper;

    public ProductUsecase(ProductJpaRepository productJpaRepository, CategoryJpaRepository categoryJpaRepository,
            ProductEntityMapper productEntityMapper) {
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

        // // ✅ ผูก Variant กับ Product และ Option กับ Variant
         for (ProductOptionEntity productOption : _product.getProductOptions()) {
             productOption.setProduct(_product); // 🟢 กำหนด product ให้ variant
             for (ProductOptionValueEntity optionValue : productOption.getProductOptionValues()) {
                 optionValue.setProductOption(productOption); // 🟢 กำหนด variant ให้ variantOption
             }
         }
        productJpaRepository.save(_product);
    }

    @Override
    public void deleteProductById(Long id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts(int page, int size) {
        return productJpaRepository.findAll().stream().map(productEntityMapper::toModel).toList();
    }

    @Override
    public Product getProductByName(String name) {
        return productEntityMapper.toModel(productJpaRepository.findByNameContainingIgnoreCase(name).orElse(null));
    }

    @Override
    public Product getProductById(Long id) {
        return productEntityMapper.toModel(productJpaRepository.findById(id).orElse(null));
    }

    @Override
    public List<Product> filterProduct(DataTableFilter filter) {
        Specification<ProductEntity> spec = DataTableSpecification.filterBy(filter);
        return productJpaRepository.findAll(
                spec,
                PageRequest.of(filter.getPage(), filter.getSize())).stream().map(productEntityMapper::toModel).toList();
    }

    @Override
    public Long countProduct() {
        return productJpaRepository.count();
    }

}
