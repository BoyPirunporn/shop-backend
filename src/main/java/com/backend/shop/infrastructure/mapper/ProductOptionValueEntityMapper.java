package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.ProductOptionValue;
import com.backend.shop.infrastructure.entity.ProductOptionValueEntity;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductOptionValueEntityMapper {

    @Mapping(target = "productOption" , ignore = true)
    ProductOptionValue toModel(ProductOptionValueEntity entity);
    ProductOptionValueEntity toEntity(ProductOptionValue model);
    
}
