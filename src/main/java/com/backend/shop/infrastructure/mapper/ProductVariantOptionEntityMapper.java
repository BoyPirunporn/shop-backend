package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.ProductVariantOption;
import com.backend.shop.infrastructure.entity.ProductVariantOptionEntity;

@Mapper(componentModel = "spring",uses = {VariantImageEntityMapper.class, CategoryEntityMapper.class, ProductOptionValueEntityMapper.class})
public interface ProductVariantOptionEntityMapper {

    @Mapping(target = "productVariant",ignore = true)
    ProductVariantOptionEntity toEntity(ProductVariantOption model);
    @Mapping(target = "productVariant",ignore = true)
    ProductVariantOption toModel(ProductVariantOptionEntity entity);
}
