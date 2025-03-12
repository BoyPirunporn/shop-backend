package com.backend.shop.domains.usecase;

import java.util.List;

import com.backend.shop.domains.models.Product;

public interface IProductUsecase {
    Product createProduct(Product product);
    List<Product> getAllProducts();
}
