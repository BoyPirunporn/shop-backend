package com.backend.shop.infrastructure.mapper.orders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.orders.OrderItem;
import com.backend.shop.infrastructure.entity.ProductOptionValueEntity;
import com.backend.shop.infrastructure.entity.order.OrderItemEntity;
import com.backend.shop.infrastructure.mapper.ProductOptionEntityMapper;

@Mapper(componentModel = "spring", uses = {
        ProductOptionEntityMapper.class,
        ProductOptionValueEntity.class,
        PaymentEntityMapper.class, })
public interface OrderItemEntityMapper {

    @Mapping(target = "order", ignore = true)
    // @Mapping(target = "productOptionValue.productOption", ignore = true)
    OrderItem toModel(OrderItemEntity entity);

    // @Mapping(target = "productOptionValue.productOption", ignore = true)
    @Mapping(target = "order.orderItems", ignore = true)
    @Mapping(target = "order.user", source = "order.user")
    OrderItemEntity toEntity(OrderItem model);

}
