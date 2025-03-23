package com.backend.shop.domains.models.orders;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.backend.shop.domains.models.BaseModel;
import com.backend.shop.domains.models.ProductOptionValue;

public class OrderItem extends BaseModel {

    private Order order;
    private Set<ProductOptionValue> selectedOptionValues = new HashSet<>();
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<ProductOptionValue> getSelectedOptionValues() {
        return selectedOptionValues;
    }

    public void setSelectedOptionValues(Set<ProductOptionValue> selectedOptionValues) {
        this.selectedOptionValues = selectedOptionValues;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderItem [quantity=" + quantity + ", unitPrice=" + unitPrice
                + ", totalPrice=" + totalPrice + "]";
    }

}
