package com.backend.shop.infrastructure.mapper;

import com.backend.shop.domains.models.Product;
import com.backend.shop.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {ProductVariantMapper.class})
public interface ProductMapper {

    ProductEntity toEntity(Product product);
    Product toModel(ProductEntity product);
}
