package com.backend.shop.applications.dto.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String mainImage;
    private String category;
    private List<ProductVariantDTO> productVariants = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime updatedAt;

    


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
    public String getMainImage() {
        return mainImage;
    }
    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public List<ProductVariantDTO> getProductVariants() {
        return productVariants;
    }
    public void setProductVariants(List<ProductVariantDTO> productVariants) {
        this.productVariants = productVariants;
    }
    @Override
    public String toString() {
        return "ProductDTO [name=" + name + ", description=" + description + ", price=" + price + ", mainImage="
                + mainImage + ", category=" + category + "]";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
