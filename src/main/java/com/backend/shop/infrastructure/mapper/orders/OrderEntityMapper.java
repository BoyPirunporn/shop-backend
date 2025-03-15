package com.backend.shop.infrastructure.mapper.orders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.orders.Order;
import com.backend.shop.domains.models.orders.ShippingAddressModel;
import com.backend.shop.infrastructure.entity.order.OrderEntity;
import com.backend.shop.infrastructure.entity.order.ShippingAddress;

@Mapper(componentModel = "spring",uses = {
    OrderItemEntityMapper.class,
    PaymentEntityMapper.class
})
public interface OrderEntityMapper {
    

    Order toModel(OrderEntity entity);
    OrderEntity toEntity(Order model);

    // ✅ เพิ่ม mapping method สำหรับ ShippingAddress
    ShippingAddressModel toModel(com.backend.shop.infrastructure.entity.order.ShippingAddress entity);

    ShippingAddress toEntity(ShippingAddressModel model);
}
