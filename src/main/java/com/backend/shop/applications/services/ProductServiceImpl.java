package com.backend.shop.applications.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.interfaces.IProductService;
import com.backend.shop.domains.models.Product;
import com.backend.shop.domains.usecase.IProductUsecase;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductUsecase productUsecase;

    public ProductServiceImpl(IProductUsecase productUsecase) {
        this.productUsecase = productUsecase;
    }


    @Override
    public List<ProductDTO> getAllProduct() {
    //   List<Product> products = productUsecase.getAllProducts();
      return new ArrayList<>();
    }

    @Override
    public void createProduct(ProductDTO product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }
    
}
