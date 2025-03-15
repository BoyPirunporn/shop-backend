package com.backend.shop.applications.dto.order;

import java.time.LocalDateTime;

import com.backend.shop.domains.enums.enumOrder.EPaymentMethod;
import com.backend.shop.domains.enums.enumOrder.EPaymentStatus;

public class PaymentDTO {
    private Long id;
    private LocalDateTime paymentDate;
    private EPaymentMethod paymentMethod;
    private EPaymentStatus paymentStatus;

    
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    
}
