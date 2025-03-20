package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.ProductOption;
import com.backend.shop.infrastructure.entity.ProductOptionEntity;

@Mapper(componentModel = "spring", uses = {ProductOptionValueEntityMapper.class})
public interface ProductOptionEntityMapper {

    @Mapping(source="product.productOptions",target = "product.productOptions", ignore = true)
    @Mapping(target = "product.category", ignore = true)
    ProductOption toModel(ProductOptionEntity entity);

    @Mapping(source="product.productOptions",target = "product.productOptions", ignore = true)
    @Mapping(target = "product.category", ignore = true)
    ProductOptionEntity toEntity(ProductOption model);
}
