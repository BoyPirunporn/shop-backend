package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.ProductOption;
import com.backend.shop.infrastructure.entity.ProductOptionEntity;

@Mapper(componentModel = "spring",uses = {ProductOptionValueMapper.class})
public interface ProductOptionMapper {
    
    @Mapping(target = "id",source = "id")
    ProductOption toModel(ProductOptionEntity entity);
    ProductOptionEntity toEntity(ProductOption model);
}
