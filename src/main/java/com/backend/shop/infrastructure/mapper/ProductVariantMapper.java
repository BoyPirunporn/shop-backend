package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.ProductVariant;
import com.backend.shop.infrastructure.entity.ProductVariantEntity;

@Mapper(componentModel = "spring",uses = {ProductVariantOptionMapper.class,VariantImageMapper.class})
public interface ProductVariantMapper {

    ProductVariantEntity toEntity(ProductVariant productVariant);
    ProductVariant toModel(ProductVariantEntity productVariant);
}
