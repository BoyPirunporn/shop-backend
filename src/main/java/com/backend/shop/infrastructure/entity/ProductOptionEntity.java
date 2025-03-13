package com.backend.shop.infrastructure.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "product_option")
public class ProductOptionEntity extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "productOption",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductOptionValueEntity> productOptionValues = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductOptionValueEntity> getProductOptionValues() {
        return productOptionValues;
    }

    public void setProductOptionValues(List<ProductOptionValueEntity> productOptionValues) {
        this.productOptionValues = productOptionValues;
    }


    
}
