package com.backend.shop.applications.mapper;


import com.backend.shop.applications.dto.product.ProductOptionDTO;
import com.backend.shop.domains.models.ProductOption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductOptionModelMapper {

    ProductOption toModel(ProductOptionDTO dto);
    ProductOptionDTO toDTO(ProductOption model);
}
