package com.backend.shop.domains.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductVariant {

    private Long id;
    private BigDecimal price;
    private  String sku;
    private Integer stock;
    private Product product;
    private VariantImage variantImage;
    private List<ProductVariantOption> productVariantOptions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public VariantImage getVariantImage() {
        return variantImage;
    }

    public void setVariantImage(VariantImage variantImage) {
        this.variantImage = variantImage;
    }

    public List<ProductVariantOption> getProductVariantOptions() {
        return productVariantOptions;
    }

    public void setProductVariantOptions(List<ProductVariantOption> productVariantOptions) {
        this.productVariantOptions = productVariantOptions;
    }

    @Override
    public String toString() {
        return "ProductVariant [id=" + id + ", price=" + price + ", sku=" + sku + ", stock=" + stock + "]";
    }

    

}
