package com.backend.shop.infrastructure.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.Product;
import com.backend.shop.domains.usecase.IProductUsecase;
import com.backend.shop.infrastructure.entity.ProductEntity;
import com.backend.shop.infrastructure.mapper.ProductMapper;
import com.backend.shop.infrastructure.repository.ProductJpaRepository;

@Service
public class ProductUsecase implements IProductUsecase {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    public ProductUsecase(ProductJpaRepository productJpaRepository,ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product createProduct(Product product) {
       ProductEntity saveProduct = productJpaRepository.save(productMapper.toEntity(product));
       return productMapper.toModel(saveProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }
    
}
