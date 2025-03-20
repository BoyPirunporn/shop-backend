package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.ProductOptionValue;
import com.backend.shop.infrastructure.entity.ProductOptionValueEntity;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductOptionValueEntityMapper {

    @Mapping(target = "productOption.productOptionValues", ignore = true)
    @Mapping(target = "productOption.product", ignore = true)
    ProductOptionValue toModel(ProductOptionValueEntity entity);

    @Mapping(target = "productOption.productOptionValues", ignore = true)
    @Mapping(target = "productOption.product", ignore = true)
    ProductOptionValueEntity toEntity(ProductOptionValue model);

}
