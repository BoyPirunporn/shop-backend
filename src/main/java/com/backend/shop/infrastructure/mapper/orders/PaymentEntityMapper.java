package com.backend.shop.infrastructure.mapper.orders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.orders.Payment;
import com.backend.shop.infrastructure.entity.order.PaymentEntity;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {

    @Mapping(target="order.payment",ignore = true)
    @Mapping(target="order.orderItems",ignore = true)
    PaymentEntity toEntity(Payment model);
    
    @Mapping(target="order",ignore = true)
    Payment toModel(PaymentEntity entity);
}
