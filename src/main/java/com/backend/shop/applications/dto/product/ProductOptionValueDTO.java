package com.backend.shop.applications.dto.product;

import jakarta.validation.constraints.NotBlank;

public class ProductOptionValueDTO {
    private Long id;
    @NotBlank
    private String value;

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
    
}
