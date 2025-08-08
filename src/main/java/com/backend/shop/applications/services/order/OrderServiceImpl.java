package com.backend.shop.applications.services.order;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.order.OrderDTO;
import com.backend.shop.applications.dto.order.request.OrderRequest;
import com.backend.shop.applications.interfaces.IAuthService;
import com.backend.shop.applications.interfaces.IOrderService;
import com.backend.shop.applications.mapper.OrderModelMapper;
import com.backend.shop.domains.models.orders.Order;
import com.backend.shop.domains.usecase.IOrderUsecase;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    private final IOrderUsecase orderUsecase;
    private final OrderModelMapper orderModelMapper;

    public OrderServiceImpl(
            IOrderUsecase orderUsecase,
            OrderModelMapper orderModelMapper,
            IAuthService authService) {
        this.orderUsecase = orderUsecase;
        this.orderModelMapper = orderModelMapper;
    }

    @Override
    public void createOrder(OrderRequest order) {
        Order orderModel = orderModelMapper.orderRequestToModel(order);
        orderUsecase.createOrder(orderModel);
    }

    @Override
    public void updateOrder(OrderDTO order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
    }

    @Override
    public void deleteOrder(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public List<OrderDTO> getAllOrder(int page, int size) {
        return orderUsecase.getAllOrder(page, size).stream().map(orderModelMapper::toDTO).toList();

    }

}
