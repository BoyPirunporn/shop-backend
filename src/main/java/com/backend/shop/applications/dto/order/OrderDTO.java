package com.backend.shop.applications.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.backend.shop.domains.enums.enumOrder.EOrderStatus;

public class OrderDTO {
    private Long id;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> orderItems;
    private PaymentDTO payment;
    private EOrderStatus status;
    private ShippingAddressDTO shippingAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
   
    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
    public PaymentDTO getPayment() {
        return payment;
    }
    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }
    public EOrderStatus getStatus() {
        return status;
    }
    public void setStatus(EOrderStatus status) {
        this.status = status;
    }
    public ShippingAddressDTO getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(ShippingAddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
