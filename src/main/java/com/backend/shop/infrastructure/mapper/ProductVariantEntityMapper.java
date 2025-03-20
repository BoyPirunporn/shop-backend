package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.ProductVariant;
import com.backend.shop.infrastructure.entity.ProductVariantEntity;
import org.mapstruct.Mapping;

import java.util.List;

//@Mapper(componentModel = "spring",uses = {
//        ProductVariantOptionEntityMapper.class, VariantImageEntityMapper.class})
public interface ProductVariantEntityMapper {

//    @Mapping(target = "product", ignore = true)
//    @Mapping(target = "productVariantOptions", source = "productVariantOptions")
//    ProductVariantEntity toEntity(ProductVariant productVariant);
//    @Mapping(target = "product", ignore = true)
//    @Mapping(target = "productVariantOptions", source = "productVariantOptions")
//    ProductVariant toModel(ProductVariantEntity productVariant);
//
//    List<ProductVariant> toModelList(List<ProductVariantEntity> entities);
//    List<ProductVariantEntity> toEntityList(List<ProductVariant> models);
}
