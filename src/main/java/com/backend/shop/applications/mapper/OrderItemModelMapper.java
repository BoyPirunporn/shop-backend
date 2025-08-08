package com.backend.shop.applications.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.applications.dto.order.OrderItemDTO;
import com.backend.shop.domains.models.orders.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemModelMapper {

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product.category",ignore = true)
    @Mapping(target = "product.productOptions",ignore = true)
//    @Mapping(target = "productOptionValue.productOption", ignore = true)
    OrderItem toModel(OrderItemDTO dto);

    
    @Mapping(target = "product.category",source = "product.category")
    OrderItemDTO toDTO(OrderItem model);
}
