package com.backend.shop.infrastructure.usecase;

import java.util.List;

import com.backend.shop.domains.models.ProductOption;
import com.backend.shop.domains.usecase.IProductOptionUsecase;
import com.backend.shop.infrastructure.entity.ProductOptionEntity;
import com.backend.shop.infrastructure.entity.ProductOptionValueEntity;
import com.backend.shop.infrastructure.mapper.ProductOptionEntityMapper;
import com.backend.shop.infrastructure.repository.ProductOptionJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductOptionUsecase implements IProductOptionUsecase {

    private final ProductOptionJpaRepository productOptionRepository;
    private final ProductOptionEntityMapper productOptionMapper;

    public ProductOptionUsecase(ProductOptionJpaRepository productOptionRepository, ProductOptionEntityMapper productOptionMapper) {
        this.productOptionRepository = productOptionRepository;
        this.productOptionMapper = productOptionMapper;
    }

    @Override
    @Transactional
    public void createOption(ProductOption productOption) {
        ProductOptionEntity productOptionEntity = productOptionMapper.toEntity(productOption);
        for(ProductOptionValueEntity productOptionValue : productOptionEntity.getProductOptionValues()){
            productOptionValue.setProductOption(productOptionEntity);
        }
        productOptionRepository.save(productOptionEntity);
    }

    @Override
    public void updateOption(ProductOption productOption) {
        productOptionRepository.save(productOptionMapper.toEntity(productOption));
    }

    @Override
    public void deleteOption(Long id) {
        productOptionRepository.deleteById(id);
    }

    @Override
    public List<ProductOption> getAllProduct(int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return productOptionRepository.findAll(pageable).stream().map(productOptionMapper::toModel).toList();
    }

    @Override
    public ProductOption getById(Long id) {
        return productOptionMapper.toModel(productOptionRepository.findById(id).orElse(null));
    }

}
