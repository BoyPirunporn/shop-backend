package com.backend.shop.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "product_option_value")
public class ProductOptionValueEntity extends BaseEntity {

    private String value;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_option_id",nullable = false)
    private ProductOptionEntity productOption;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProductOptionEntity getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOptionEntity productOption) {
        this.productOption = productOption;
    }


}
