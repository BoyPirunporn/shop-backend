package com.backend.shop.applications.services.product;

import java.util.List;

import com.backend.shop.applications.mapper.ProductModelMapper;
import com.backend.shop.domains.models.Product;
import com.backend.shop.infrastructure.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.interfaces.IProductService;
import com.backend.shop.domains.usecase.IProductUsecase;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductUsecase productUsecase;
    private final ProductModelMapper productModelMapper;

    public ProductServiceImpl(IProductUsecase productUsecase, ProductModelMapper productModelMapper) {
        this.productUsecase = productUsecase;
        this.productModelMapper = productModelMapper;
    }


    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productUsecase.getProductByName(name);
        if(product == null) {
            throw new BaseException("Product name not found.", HttpStatus.BAD_REQUEST);
        }
        return productModelMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProduct(int page, int size) {
      return productUsecase.getAllProducts(page,size).stream().map(productModelMapper::toDTO).toList();
    }



    @Override
    public void createProduct(ProductDTO product) {
       productUsecase.createProduct(productModelMapper.toModel(product));
    }

    @Override
    public void updateProduct(ProductDTO product) {
        productUsecase.createProduct(productModelMapper.toModel(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productUsecase.deleteProductById(id);
    }

}
