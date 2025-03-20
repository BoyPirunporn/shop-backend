package com.backend.shop.infrastructure.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

// @Entity(name = "variant_image")
public class VariantImageEntity extends BaseEntity {

    private String url;

    @OneToOne(mappedBy = "variantImage")
    @JoinColumn(name = "product_variant_id")
    private ProductVariantEntity productVariant;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProductVariantEntity getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariantEntity productVariant) {
        this.productVariant = productVariant;
    }

}
