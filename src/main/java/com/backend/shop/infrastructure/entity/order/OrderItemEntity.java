package com.backend.shop.infrastructure.entity.order;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.backend.shop.infrastructure.entity.BaseEntity;
import com.backend.shop.infrastructure.entity.ProductOptionEntity;
import com.backend.shop.infrastructure.entity.ProductOptionValueEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity(name = "order_item")
public class OrderItemEntity extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToMany
    @JoinTable(
            name = "order_item_option_values",
            joinColumns = @JoinColumn(name = "order_item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_option_value_id")
    )
    private Set<ProductOptionValueEntity> selectedOptionValues = new HashSet<>(); // ✅ เก็บหลาย Option Value

    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Set<ProductOptionValueEntity> getSelectedOptionValues() {
        return selectedOptionValues;
    }

    public void setSelectedOptionValues(Set<ProductOptionValueEntity> selectedOptionValues) {
        this.selectedOptionValues = selectedOptionValues;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

}
