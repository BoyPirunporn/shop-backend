package com.backend.shop.infrastructure.mapper.orders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.orders.OrderItem;
import com.backend.shop.infrastructure.entity.order.OrderItemEntity;
import com.backend.shop.infrastructure.mapper.ProductVariantEntityMapper;
import com.backend.shop.infrastructure.mapper.ProductVariantOptionEntityMapper;

@Mapper(componentModel = "spring", uses = {
        PaymentEntityMapper.class,
        ProductVariantEntityMapper.class,
        ProductVariantOptionEntityMapper.class })
public interface OrderItemEntityMapper {

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "productVariant.product", ignore = true)
    OrderItem toModel(OrderItemEntity entity);

    @Mapping(target = "productVariant.product", ignore = true)
    @Mapping(target = "productVariant.productVariantOptions", ignore = true)
    @Mapping(target = "order.orderItems", ignore = true)
    @Mapping(target = "order.user", source = "order.user")
    OrderItemEntity toEntity(OrderItem model);

}
