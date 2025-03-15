package com.backend.shop.domains.models.orders;

import java.time.LocalDateTime;

import com.backend.shop.domains.enums.enumOrder.EPaymentMethod;
import com.backend.shop.domains.enums.enumOrder.EPaymentStatus;
import com.backend.shop.domains.models.BaseModel;

public class Payment extends BaseModel{
    private Order order;
    private LocalDateTime paymentDate;
    private EPaymentMethod paymentMethod;
    private EPaymentStatus paymentStatus;
   
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
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
