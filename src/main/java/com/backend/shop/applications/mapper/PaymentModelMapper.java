package com.backend.shop.applications.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.applications.dto.order.PaymentDTO;
import com.backend.shop.domains.models.orders.Payment;

@Mapper(componentModel = "spring")
public interface PaymentModelMapper {

    @Mapping(target = "order",ignore = true)
    Payment toModel(PaymentDTO dto);
    PaymentDTO toDto(Payment model);
}
