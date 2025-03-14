package com.backend.shop.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class FileStoreProperties {

    private String fileStore; //ชื่อ properties ใน yaml application.file-store

    public String getFileStore() {
        return fileStore;
    }

    public void setFileStore(String fileStore) {
        this.fileStore = fileStore;
    }

   
}