package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.VariantImage;
import com.backend.shop.infrastructure.entity.VariantImageEntity;

@Mapper(componentModel = "spring")
public interface VariantImageMapper {

    VariantImage toModel(VariantImageEntity entity);
    VariantImageEntity toEntity(VariantImage model);
    
}
