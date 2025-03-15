package com.backend.shop.applications.interfaces;

import java.io.IOException;
import java.util.List;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.dto.product.request.ProductRequestDTO;

public interface IProductService {
    ProductDTO getProductByName(String name);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProduct(int page,int size);
    void createProduct(ProductRequestDTO product) throws IOException;
    void updateProduct(ProductRequestDTO product)  throws IOException;
    void deleteProduct(Long id);
}
