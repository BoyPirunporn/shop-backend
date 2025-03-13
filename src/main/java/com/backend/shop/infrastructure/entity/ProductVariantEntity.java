package com.backend.shop.infrastructure.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "product_variant")
public class ProductVariantEntity extends BaseEntity {
    private BigDecimal price;
    private Integer stock;
    private String sku;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private ProductEntity product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id",referencedColumnName = "id")
    private VariantImageEntity variantImage;

    @OneToMany(mappedBy = "productVariant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductVariantOptionEntity> productVariantOptions = new ArrayList<>();

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

   
    public List<ProductVariantOptionEntity> getProductVariantOptions() {
        return productVariantOptions;
    }

    public void setProductVariantOptions(List<ProductVariantOptionEntity> productVariantOptions) {
        this.productVariantOptions = productVariantOptions;
    }

    public VariantImageEntity getVariantImage() {
        return variantImage;
    }

    public void setVariantImage(VariantImageEntity variantImage) {
        this.variantImage = variantImage;
    }

 
   

   

   
    
    

   
}
