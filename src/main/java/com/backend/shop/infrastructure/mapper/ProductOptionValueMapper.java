package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.ProductOptionValue;
import com.backend.shop.infrastructure.entity.ProductOptionValueEntity;

@Mapper(componentModel = "spring")
public interface ProductOptionValueMapper {
    ProductOptionValue toModel(ProductOptionValueEntity entity);
    ProductOptionValueEntity toEntity(ProductOptionValue model);
    
}
