package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.VariantImage;
import com.backend.shop.infrastructure.entity.VariantImageEntity;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VariantImageEntityMapper {

    @Mapping(target = "productVariant",ignore = true)
    VariantImage toModel(VariantImageEntity entity);
    @Mapping(target = "productVariant",ignore = true)
    VariantImageEntity toEntity(VariantImage model);
    
}
