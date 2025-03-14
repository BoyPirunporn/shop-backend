package com.backend.shop.applications.dto.product.request;

import org.springframework.web.multipart.MultipartFile;

public class VariantImageRequestDTO {
    private MultipartFile url;

    public MultipartFile getUrl() {
        return url;
    }

    public void setUrl(MultipartFile url) {
        this.url = url;
    }

    
}
