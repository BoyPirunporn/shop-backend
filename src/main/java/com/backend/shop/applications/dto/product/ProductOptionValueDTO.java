package com.backend.shop.applications.dto.product;

import jakarta.validation.constraints.NotBlank;

public class ProductOptionValueDTO {

    private Long id;
    @NotBlank
    private String value;

    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
