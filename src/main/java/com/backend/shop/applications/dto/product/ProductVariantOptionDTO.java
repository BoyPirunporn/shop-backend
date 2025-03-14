package com.backend.shop.applications.dto.product;

public class ProductVariantOptionDTO {
    private Long id;
    private ProductOptionValueDTO productOptionValue;


    public ProductOptionValueDTO getProductOptionValue() {
        return productOptionValue;
    }

    public void setProductOptionValue(ProductOptionValueDTO productOptionValue) {
        this.productOptionValue = productOptionValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
