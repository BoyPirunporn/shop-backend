package com.backend.shop.applications.dto.product.request;

import jakarta.validation.constraints.NotBlank;

public class ProductOptionValueRequest {

    private Long id;
    @NotBlank
    private String value;

    private Object image;

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

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

}
