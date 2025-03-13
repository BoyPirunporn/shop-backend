package com.backend.shop.applications.services.product.productOption;

import java.util.List;

import com.backend.shop.applications.dto.product.ProductOptionDTO;
import com.backend.shop.applications.mapper.ProductOptionModelMapper;
import com.backend.shop.domains.usecase.IProductOptionUsecase;
import org.springframework.stereotype.Service;

import com.backend.shop.applications.interfaces.IProductOptionService;
import com.backend.shop.domains.models.ProductOption;


@Service
public class ProductOptionServiceImpl implements  IProductOptionService
{

    private final IProductOptionUsecase productOptionUsecase;
    private final ProductOptionModelMapper productOptionModelMapper;

    public ProductOptionServiceImpl(IProductOptionUsecase productOptionUsecase, ProductOptionModelMapper productOptionModelMapper) {
        this.productOptionUsecase = productOptionUsecase;
        this.productOptionModelMapper = productOptionModelMapper;
    }

    @Override
    public void createOption(ProductOptionDTO option) {
        ProductOption productOption = productOptionModelMapper.toModel(option);
        productOptionUsecase.createOption(productOption);
    }

    @Override
    public void updateOption(ProductOptionDTO option) {

    }

    @Override
    public void deleteOption(Long id) {

    }

    @Override
    public List<ProductOptionDTO> getAllProductOption(int page, int size) {
        return productOptionUsecase.getAllProduct(page,size).stream().map(productOptionModelMapper::toDTO).toList();
    }

    @Override
    public ProductOptionDTO getById(Long id) {
        return productOptionModelMapper.toDTO(productOptionUsecase.getById(id));
    }
}
