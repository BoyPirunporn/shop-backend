package com.backend.shop.infrastructure.mapper;

import com.backend.shop.domains.models.Product;
import com.backend.shop.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {
        CategoryEntityMapper.class,
        ProductOptionEntityMapper.class
})
public interface ProductEntityMapper {
    @Mapping(target = "productOptions", source = "productOptions")
    ProductEntity toEntity(Product product);
    @Mapping(target = "productOptions", source = "productOptions")
    Product toModel(ProductEntity product);


}
