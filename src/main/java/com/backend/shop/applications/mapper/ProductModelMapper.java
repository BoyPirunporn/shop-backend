package com.backend.shop.applications.mapper;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.dto.product.request.ProductRequestDTO;
import com.backend.shop.domains.models.Category;
import com.backend.shop.domains.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductModelMapper {

    @Mapping(target = "category",source = "category",qualifiedByName = "categoryStringToModel")
    Product toModel(ProductDTO dto);
    @Mapping(target = "category",source = "category",qualifiedByName = "categoryModelToString")
    ProductDTO toDTO(Product model);

    @Mapping(target = "mainImage",ignore = true)
    @Mapping(target = "category",source = "category",qualifiedByName = "categoryStringToModel")
    @Mapping(target = "productVariants",ignore = true)
    Product toModel(ProductRequestDTO requestDTO);



    @Named("categoryStringToModel")
    default Category categoryStringToModel(String category){
        return new Category(category);
    }
    @Named("categoryModelToString")
    default String categoryStringToModel(Category category){
        return category.getName();
    }

}
