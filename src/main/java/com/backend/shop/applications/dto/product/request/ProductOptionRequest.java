package com.backend.shop.applications.dto.product.request;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class ProductOptionRequest {

    private Long id;
    private Boolean enableImage;
    @NotBlank
    private String name;
    @NotEmpty
    private List<ProductOptionValueRequest> productOptionValues = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnableImage() {
        return enableImage;
    }

    public void setEnableImage(Boolean enableImage) {
        this.enableImage = enableImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductOptionValueRequest> getProductOptionValues() {
        return productOptionValues;
    }

    public void setProductOptionValues(List<ProductOptionValueRequest> productOptionValues) {
        this.productOptionValues = productOptionValues;
    }

    
}
