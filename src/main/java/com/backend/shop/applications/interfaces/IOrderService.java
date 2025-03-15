package com.backend.shop.applications.interfaces;

import java.util.List;

import com.backend.shop.applications.dto.order.OrderDTO;
import com.backend.shop.applications.dto.order.request.OrderRequest;

public interface IOrderService {
    void createOrder(OrderRequest order);
    void updateOrder(OrderDTO order);
    void deleteOrder(Long id);
    List<OrderDTO> getAllOrder(int page,int size);
}
