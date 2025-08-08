package com.backend.shop.infrastructure.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "product_option")
public class ProductOptionEntity extends BaseEntity {
    private String name;
    private Boolean enableImage;
    @Column(name = "one_must_be_chosen", columnDefinition = "boolean default false")
    private boolean oneMustBeChosen;

    @Column(name = "many_can_be_chosen",columnDefinition = "boolean default false")
    private boolean manyCanBeChosen;

    @Column(nullable = false)
    private int lengthSelect;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private ProductEntity product;

    @OneToMany(mappedBy = "productOption",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductOptionValueEntity> productOptionValues = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductOptionValueEntity> getProductOptionValues() {
        return productOptionValues;
    }

    public void setProductOptionValues(List<ProductOptionValueEntity> productOptionValues) {
        this.productOptionValues = productOptionValues;
    }

    public Boolean getEnableImage() {
        return enableImage;
    }

    public void setEnableImage(Boolean enableImage) {
        this.enableImage = enableImage;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
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
