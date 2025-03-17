package com.backend.shop.applications.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String createPath(MultipartFile file,String savePath) throws IOException;
}
