package com.backend.shop.applications.dto.product;

import java.util.ArrayList;
import java.util.List;

public class ProductOptionDTO {
    private String name;
    private List<ProductOptionValueDTO> productOptionValues = new ArrayList<>();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ProductOptionValueDTO> getProductOptionValues() {
        return productOptionValues;
    }
    public void setProductOptionValues(List<ProductOptionValueDTO> productOptionValues) {
        this.productOptionValues = productOptionValues;
    }

    
}
