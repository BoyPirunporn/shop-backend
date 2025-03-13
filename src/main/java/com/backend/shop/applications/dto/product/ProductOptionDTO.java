package com.backend.shop.applications.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class ProductOptionDTO {

    private Long id;

    @NotBlank
    private String name;
    @NotEmpty
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
