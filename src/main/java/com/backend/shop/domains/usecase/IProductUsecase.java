package com.backend.shop.domains.usecase;

import java.util.List;

import com.backend.shop.domains.datatable.product.ProductFilter;
import com.backend.shop.domains.models.Product;
import com.backend.shop.domains.datatable.DataTableFilter;

public interface IProductUseCase {
    List<Product> filterProduct(DataTableFilter filter);
    List<Product> filterProduct(ProductFilter filter);
    List<Product> getAllProducts(int page,int size);
    Product getProductByName(String name);
    Product getProductById(Long id);
    void createProduct(Product product);
    void deleteProductById(Long id);
    Long countProduct();
}
