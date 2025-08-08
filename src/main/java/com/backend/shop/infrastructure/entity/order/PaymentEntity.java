package com.backend.shop.infrastructure.entity.order;

import java.time.LocalDateTime;

import com.backend.shop.domains.enums.enumOrder.EPaymentMethod;
import com.backend.shop.domains.enums.enumOrder.EPaymentStatus;
import com.backend.shop.infrastructure.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "payment")
public class PaymentEntity extends BaseEntity {


    @OneToOne
    @JoinColumn(name = "order_id",nullable = false)
    private OrderEntity order;

    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private EPaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private EPaymentStatus paymentStatus;
    public OrderEntity getOrder() {
        return order;
    }
    public void setOrder(OrderEntity order) {
        this.order = order;
    }
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public EPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(EPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public EPaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(EPaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    
}
