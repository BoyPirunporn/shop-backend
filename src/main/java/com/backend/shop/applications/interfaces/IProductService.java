package com.backend.shop.applications.interfaces;

import java.util.List;

import com.backend.shop.applications.dto.product.ProductDTO;

public interface IProductService {
    List<ProductDTO> getAllProduct();
    void createProduct(ProductDTO product);
}
