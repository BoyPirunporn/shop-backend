package com.backend.shop.applications.interfaces;

import java.util.List;

import com.backend.shop.applications.dto.product.ProductDTO;

public interface IProductService {
    ProductDTO getProductByName(String name);
    List<ProductDTO> getAllProduct(int page,int size);
    void createProduct(ProductDTO product);
    void updateProduct(ProductDTO product);
    void deleteProduct(Long id);
}
