package com.backend.shop.applications.dto.product.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductRequestDTO {
    private Long id;
   
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    private String name;
    private String description;
    private BigDecimal price;
    private Object mainImage;
    private String category;
    private List<ProductVariantRequestDTO> productVariants = new ArrayList<>();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Object getMainImage() {
        return mainImage;
    }
    public void setMainImage(Object mainImage) {
        this.mainImage = mainImage;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public List<ProductVariantRequestDTO> getProductVariants() {
        return productVariants;
    }
    public void setProductVariants(List<ProductVariantRequestDTO> productVariants) {
        this.productVariants = productVariants;
    }

    

}
