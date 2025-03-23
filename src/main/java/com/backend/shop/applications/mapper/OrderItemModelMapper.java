package com.backend.shop.applications.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.applications.dto.order.OrderItemDTO;
import com.backend.shop.domains.models.orders.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemModelMapper {

    @Mapping(target = "order", ignore = true)
//    @Mapping(target = "productOptionValue.productOption", ignore = true)
    OrderItem toModel(OrderItemDTO dto);

    OrderItemDTO toDTO(OrderItem model);
}
