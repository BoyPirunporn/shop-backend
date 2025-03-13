package com.backend.shop.applications.interfaces;

import java.util.List;

import com.backend.shop.applications.dto.product.ProductOptionDTO;

public interface IProductOptionService {
    void createOption(ProductOptionDTO option);
    void updateOption(ProductOptionDTO option);
    void deleteOption(Long id);
    List<ProductOptionDTO> getAllProductOption(int page, int size);
    ProductOptionDTO getById(Long id);
}
