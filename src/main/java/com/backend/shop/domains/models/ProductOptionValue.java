package com.backend.shop.domains.models;

public class ProductOptionValue {

    private Long id;
    private String value;
    private ProductOption productOption;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public ProductOption getProductOption() {
        return productOption;
    }
    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }


   
}
