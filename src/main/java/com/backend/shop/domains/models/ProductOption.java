package com.backend.shop.domains.models;

import java.util.ArrayList;
import java.util.List;

public class ProductOption {

    private Long id;
    private String name;
    private List<ProductOptionValue> productOptionValues = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductOptionValue> getProductOptionValues() {
        return productOptionValues;
    }

    public void setProductOptionValues(List<ProductOptionValue> productOptionValues) {
        this.productOptionValues = productOptionValues;
    }
}
