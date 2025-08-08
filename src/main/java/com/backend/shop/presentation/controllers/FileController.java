package com.backend.shop.presentation.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.infrastructure.config.FileStoreProperties;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileStoreProperties fileStoreProperties;

    public FileController(FileStoreProperties fileStoreProperties) {
        this.fileStoreProperties = fileStoreProperties;
    }

    @GetMapping("/**")
    public ResponseEntity<?> getHello(HttpServletRequest request) throws Exception, IOException {
        String path = request.getRequestURI().substring("/files/".length());
        File file = new File(Paths.get(fileStoreProperties.getFileStore(), path).toString());
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new FileSystemResource(file);

        String contentType = "image/jpeg";  // สำหรับ JPEG เรากำหนด content type เป็น image/jpeg
        if (path.endsWith(".png")) {
            contentType = "image/png"; // ถ้าไฟล์เป็น PNG
        } else if (path.endsWith(".gif")) {
            contentType = "image/gif"; // ถ้าไฟล์เป็น GIF
        }
         // ส่งไฟล์กลับไปให้ client เป็นรูปภาพ
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    // @GetMapping("/{folder}/{subFolder}/{fileName}")
    // public ResponseEntity<Resource> getFile(@PathVariable String folder,
    // @PathVariable String subFolder,
    // @PathVariable String fileName) {
    // System.out.println(fileStoreProperties.getFileStore());
    // // สร้าง path ของไฟล์จาก folder และชื่อไฟล์
    // try {
    // File file = new File(Paths.get(fileStoreProperties.getFileStore(), folder,
    // fileName).toString());

    // // ตรวจสอบว่าไฟล์มีอยู่จริงหรือไม่
    // if (!file.exists()) {
    // return ResponseEntity.notFound().build(); // ส่ง error ถ้าไฟล์ไม่พบ
    // }

    // // สร้าง Resource จากไฟล์ที่พบ
    // Resource resource = new FileSystemResource(file);

    // // ส่งไฟล์กลับไปให้ client พร้อม header ที่เหมาะสม
    // return ResponseEntity.ok()
    // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
    // file.getName())
    // .body(resource);
    // } catch (Exception e) {
    // e.printStackTrace();
    // return ResponseEntity.notFound().build(); // ส่ง error ถ้าเกิดข้อผิดพลาด
    // }
    // }

}
