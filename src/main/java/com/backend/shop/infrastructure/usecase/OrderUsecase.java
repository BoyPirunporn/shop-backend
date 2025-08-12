package com.backend.shop.infrastructure.usecase;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.orders.Order;
import com.backend.shop.domains.usecase.IOrderUseCase;
import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.entity.order.OrderEntity;
import com.backend.shop.infrastructure.entity.order.OrderItemEntity;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.backend.shop.infrastructure.mapper.orders.OrderEntityMapper;
import com.backend.shop.infrastructure.repository.OrderJpaRepository;
import com.backend.shop.infrastructure.repository.UserJapRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderUseCase implements IOrderUseCase {
private static final Logger log = LoggerFactory.getLogger(OrderUseCase.class);

    private final OrderJpaRepository orderRepository;
    private final UserJapRepository userJapRepoitory;
    private final OrderEntityMapper orderEntityMapper;

    public OrderUseCase(OrderJpaRepository orderRepository, OrderEntityMapper orderEntityMapper,
            UserJapRepository userJapRepoitory) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
        this.userJapRepoitory = userJapRepoitory;
    }

    @Override
    @Transactional
    public void createOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        orderEntity.setOrderDate(LocalDateTime.now());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            UsersEntity u = userJapRepoitory.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new BaseException("User not found", HttpStatus.BAD_REQUEST));
            orderEntity.setUser(u);

        }
        for (OrderItemEntity orderItem : orderEntity.getOrderItems()) {
            orderItem.setOrder(orderEntity);
        }
        orderEntity.getPayment().setOrder(orderEntity);

        //log.info("orderEntity -> {}", orderEntity.getPayment().getPaymentMethod());
        orderRepository.save(orderEntity);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
        ;
    }

    @Override
    public Order getByOrderId(Long id) {
        return orderEntityMapper.toModel(orderRepository.findById(id)
                .orElseThrow(() -> new BaseException("Not found order", HttpStatus.BAD_REQUEST)));
    }

    @Override
    public List<Order> getAllOrder(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAll(pageable).stream().map(orderEntityMapper::toModel).toList();
    }

}
