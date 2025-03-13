package com.backend.shop.infrastructure.mapper;

import com.backend.shop.domains.models.Product;
import com.backend.shop.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {
        CategoryEntityMapper.class,
        ProductVariantEntityMapper.class
})
public interface ProductEntityMapper {
    @Mapping(target = "productVariants", source = "productVariants")
    ProductEntity toEntity(Product product);
    @Mapping(target = "productVariants", source = "productVariants")
    Product toModel(ProductEntity product);


}
