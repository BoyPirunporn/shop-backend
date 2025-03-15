package com.backend.shop.domains.models.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.backend.shop.domains.enums.enumOrder.EOrderStatus;
import com.backend.shop.domains.models.BaseModel;
import com.backend.shop.domains.models.User;

public class Order  extends BaseModel{
    private User user;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private LocalDateTime orderDate;
    private List<OrderItem> orderItems;
    private Payment payment;
    private EOrderStatus status;
    private ShippingAddressModel shippingAddress;
    
    
    
    @Override
    public String toString() {
        return "Order [totalAmount=" + totalAmount + ", discount=" + discount + ", orderDate=" + orderDate + ", status="
                + status + "]";
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
   
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
   
   
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public EOrderStatus getStatus() {
        return status;
    }
    public void setStatus(EOrderStatus status) {
        this.status = status;
    }
    public ShippingAddressModel getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(ShippingAddressModel shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
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

    
    
}
