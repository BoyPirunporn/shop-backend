package com.backend.shop.applications.dto.product.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.backend.shop.applications.dto.category.CategoryDTO;

public class ProductRequestDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Object mainImage;
    private CategoryDTO category;
    private List<ProductOptionRequest> productOptions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<ProductOptionRequest> getProductOptions() {
        return productOptions;
    }

    public void setProductOptions(List<ProductOptionRequest> productOptions) {
        this.productOptions = productOptions;
    }
}
