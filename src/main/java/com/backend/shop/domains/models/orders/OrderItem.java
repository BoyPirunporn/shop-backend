package com.backend.shop.domains.models.orders;

import java.math.BigDecimal;

import com.backend.shop.domains.models.BaseModel;

public class OrderItem extends BaseModel {

    private Order order;
    // private ProductVariant productVariant;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // public ProductVariant getProductVariant() {
    //     return productVariant;
    // }
    // public void setProductVariant(ProductVariant productVariant) {
    //     this.productVariant = productVariant;
    // }
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
