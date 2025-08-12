package com.backend.shop.domains.usecase;

import java.util.List;

import com.backend.shop.domains.models.orders.Order;

public interface IOrderUseCase {
    void createOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Long id);
    Order getByOrderId(Long id);
    List<Order> getAllOrder(int page,int size);
}
