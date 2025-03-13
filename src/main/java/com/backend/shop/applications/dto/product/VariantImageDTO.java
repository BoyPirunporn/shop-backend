package com.backend.shop.applications.dto.product;

public class VariantImageDTO {
    private Long id;
private String url;

public String getUrl() {
    return url;
}

public void setUrl(String url) {
    this.url = url;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
