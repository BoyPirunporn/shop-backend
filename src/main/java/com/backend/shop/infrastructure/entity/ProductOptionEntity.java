package com.backend.shop.infrastructure.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "product_option")
public class ProductOptionEntity extends BaseEntity {
    private String name;
    private Boolean enableImage;

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
}
