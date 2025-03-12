package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.ProductVariantOption;
import com.backend.shop.infrastructure.entity.ProductVariantOptionEntity;

@Mapper(componentModel = "spring",uses = {VariantImageMapper.class})
public interface ProductVariantOptionMapper {
    ProductVariantOptionEntity toEntity(ProductVariantOption model);
    @Mapping(target = "id",source = "id")
    ProductVariantOption toModel(ProductVariantOptionEntity entity);
}
