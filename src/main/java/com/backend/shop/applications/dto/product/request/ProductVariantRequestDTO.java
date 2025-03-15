package com.backend.shop.applications.dto.product.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.backend.shop.applications.dto.product.ProductVariantOptionDTO;

public class ProductVariantRequestDTO {
    private Long id;
     public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    private BigDecimal price;
    private String sku;
    private Integer stock;
    private VariantImageRequestDTO variantImage;
    private List<ProductVariantOptionDTO> productVariantOptions = new ArrayList<>();
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public VariantImageRequestDTO getVariantImage() {
        return variantImage;
    }
    public void setVariantImage(VariantImageRequestDTO variantImage) {
        this.variantImage = variantImage;
    }
    public List<ProductVariantOptionDTO> getProductVariantOptions() {
        return productVariantOptions;
    }
    public void setProductVariantOptions(List<ProductVariantOptionDTO> productVariantOptions) {
        this.productVariantOptions = productVariantOptions;
    }

    
}
