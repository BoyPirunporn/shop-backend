package com.backend.shop.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.shop.infrastructure.entity.order.OrderEntity;

public interface OrderJpaRepository extends JpaRepository<OrderEntity,Long> {
}
