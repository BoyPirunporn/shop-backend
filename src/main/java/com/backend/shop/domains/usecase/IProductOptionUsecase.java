package com.backend.shop.domains.usecase;

import java.util.List;

import com.backend.shop.domains.models.ProductOption;

public interface IProductOptionUsecase {
    void createOption(ProductOption productOption);
    void updateOption(ProductOption productOption);
    void deleteOption(Long id);
    List<ProductOption> getAllProduct(int page,int size);
    ProductOption getById(Long id);
}
