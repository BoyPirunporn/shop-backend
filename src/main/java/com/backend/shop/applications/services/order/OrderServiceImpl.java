package com.backend.shop.applications.services.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.order.OrderDTO;
import com.backend.shop.applications.dto.order.request.OrderRequest;
import com.backend.shop.applications.interfaces.IAuthService;
import com.backend.shop.applications.interfaces.IOrderService;
import com.backend.shop.applications.mapper.OrderModelMapper;
import com.backend.shop.domains.models.orders.Order;
import com.backend.shop.domains.usecase.IOrderUsecase;

@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderUsecase orderUsecase;
    private final IAuthService authService;
    private final OrderModelMapper orderModelMapper;

    public OrderServiceImpl(
            IOrderUsecase orderUsecase,
            OrderModelMapper orderModelMapper,
            IAuthService authService) {
        this.orderUsecase = orderUsecase;
        this.orderModelMapper = orderModelMapper;
        this.authService = authService;
    }

    @Override
    public void createOrder(OrderRequest order) {
        Order orderModel = orderModelMapper.orderRequestToModel(order);
        System.out.println(orderModel.getOrderItems().get(0).getProduct());

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
