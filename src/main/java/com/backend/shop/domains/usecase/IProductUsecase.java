package com.backend.shop.domains.usecase;

import java.util.List;
import java.util.Optional;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.domains.models.Product;

public interface IProductUsecase {
    List<Product> getAllProducts(int page,int size);
    Product getProductByName(String name);
    void createProduct(Product product);
    void deleteProductById(Long id);
}
