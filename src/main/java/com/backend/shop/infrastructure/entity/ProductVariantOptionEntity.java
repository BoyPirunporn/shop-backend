package com.backend.shop.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "product_variant_option")
public class ProductVariantOptionEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "variant_id",nullable = false)
    private ProductVariantEntity productVariant;

    @ManyToOne
    @JoinColumn(name ="product_option_value_id",nullable = false)
    private ProductOptionValueEntity productOptionValue;

    public ProductVariantEntity getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariantEntity productVariant) {
        this.productVariant = productVariant;
    }

    public ProductOptionValueEntity getProductOptionValue() {
        return productOptionValue;
    }

    public void setProductOptionValue(ProductOptionValueEntity productOptionValue) {
        this.productOptionValue = productOptionValue;
    }


}
