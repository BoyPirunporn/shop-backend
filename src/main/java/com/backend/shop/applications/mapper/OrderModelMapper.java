package com.backend.shop.applications.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.applications.dto.order.OrderDTO;
import com.backend.shop.applications.dto.order.request.OrderRequest;
import com.backend.shop.domains.models.orders.Order;

@Mapper(componentModel = "spring",uses = {
    OrderItemModelMapper.class,
    PaymentModelMapper.class
})
public interface OrderModelMapper {
    
    Order toModel(OrderDTO dto);
    
    @Mapping(target = "user.id",ignore = true)
    @Mapping(target = "user.roles",ignore = true)
    @Mapping(target = "user.password",ignore = true)
    OrderDTO toDTO(Order model);

    @Mapping(target = "user",ignore = true)
    Order orderRequestToModel(OrderRequest request);
    
}
