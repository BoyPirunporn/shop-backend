package com.backend.shop.applications.services.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.shop.applications.interfaces.IFileService;
import com.backend.shop.infrastructure.config.FileStoreProperties;

@Service
public class FileServiceImpl implements IFileService {

    private final FileStoreProperties fileStoreProperties;

    public FileServiceImpl(FileStoreProperties fileStoreProperties) {
        this.fileStoreProperties = fileStoreProperties;
    }

    @Override
    public String createPath(MultipartFile file,String savePath) throws IOException {
        final String pathFile = fileStoreProperties.getFileStore(); // พาธที่มาจาก properties
        String pathToFile = Paths.get(savePath).toString();
        String pathName = Paths.get(pathFile, pathToFile).toString(); // สร้างพาธแบบสมบูรณ์
        
        Path destination = Paths.get(pathName);

        // สร้างไดเรกทอรีถ้ายังไม่มี
        if (Files.notExists(destination.getParent())) {
            Files.createDirectories(destination.getParent());
        }

        // เขียนไฟล์ลงในพาธ
        Files.write(destination, file.getBytes());

        return pathToFile;  // คืนค่า path ของไฟล์
    }

}
