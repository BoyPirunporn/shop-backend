package com.backend.shop.applications.dto.order.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.backend.shop.applications.dto.order.OrderItemDTO;
import com.backend.shop.applications.dto.order.PaymentDTO;
import com.backend.shop.applications.dto.order.ShippingAddressDTO;
import com.backend.shop.domains.enums.enumOrder.EOrderStatus;

public class OrderRequest {
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private LocalDateTime orderDate;
    private EOrderStatus status;
    private List<OrderItemDTO> orderItems = new ArrayList<>();
    private PaymentDTO payment;
    private ShippingAddressDTO shippingAddress;

    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public EOrderStatus getStatus() {
        return status;
    }
    public void setStatus(EOrderStatus status) {
        this.status = status;
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
    public ShippingAddressDTO getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(ShippingAddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    
}
