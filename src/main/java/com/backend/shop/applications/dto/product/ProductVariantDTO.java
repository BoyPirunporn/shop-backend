package com.backend.shop.applications.dto.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductVariantDTO {
    private BigDecimal price;
    private String sku;
    private Integer stock;
    private VariantImageDTO variantImage;
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
    public VariantImageDTO getVariantImage() {
        return variantImage;
    }
    public void setVariantImage(VariantImageDTO variantImage) {
        this.variantImage = variantImage;
    }
    public List<ProductVariantOptionDTO> getProductVariantOptions() {
        return productVariantOptions;
    }
    public void setProductVariantOptions(List<ProductVariantOptionDTO> productVariantOptions) {
        this.productVariantOptions = productVariantOptions;
    }

}
