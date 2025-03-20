package com.backend.shop.infrastructure.entity.order;

import java.math.BigDecimal;

import com.backend.shop.infrastructure.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "order_item")
public class OrderItemEntity extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    // @ManyToOne
    // @JoinColumn(name = "product_variant_id",nullable = false)
    // private ProductVariantEntity productVariant;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    // public ProductVariantEntity getProductVariant() {
    //     return productVariant;
    // }
    // public void setProductVariant(ProductVariantEntity productVariant) {
    //     this.productVariant = productVariant;
    // }
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
