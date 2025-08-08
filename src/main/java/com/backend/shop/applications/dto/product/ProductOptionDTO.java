package com.backend.shop.applications.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class ProductOptionDTO {

    private Long id;
    private Boolean enableImage;
    @NotBlank
    private String name;
    private boolean oneMustBeChosen;

    private boolean manyCanBeChosen;

    private int lengthSelect;
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

    public Boolean getEnableImage() {
        return enableImage;
    }

    public void setEnableImage(Boolean enableImage) {
        this.enableImage = enableImage;
    }
    public boolean isOneMustBeChosen() {
        return oneMustBeChosen;
    }
    public void setOneMustBeChosen(boolean oneMustBeChosen) {
        this.oneMustBeChosen = oneMustBeChosen;
    }
    public boolean isManyCanBeChosen() {
        return manyCanBeChosen;
    }
    public void setManyCanBeChosen(boolean manyCanBeChosen) {
        this.manyCanBeChosen = manyCanBeChosen;
    }
    public int getLengthSelect() {
        return lengthSelect;
    }
    public void setLengthSelect(int lengthSelect) {
        this.lengthSelect = lengthSelect;
    }

    
}
