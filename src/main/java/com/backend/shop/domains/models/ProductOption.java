package com.backend.shop.domains.models;

import java.util.ArrayList;
import java.util.List;

public class ProductOption {

    private Long id;
    private String name;
    private Boolean enableImage;
    private Product product;
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

    public Boolean getEnableImage() {
        return enableImage;
    }

    public void setEnableImage(Boolean enableImage) {
        this.enableImage = enableImage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
