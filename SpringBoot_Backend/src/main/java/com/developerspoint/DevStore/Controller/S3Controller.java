package com.developerspoint.DevStore.Controller;

import com.developerspoint.DevStore.Services.S3Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class S3Controller {
    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/apk")
    public String uploadApk(@RequestParam("file") MultipartFile file,
                            @RequestParam("icon") MultipartFile icon,
                            @RequestParam("name") String name,
                            @RequestParam("version") String version,
                            @RequestParam("description") String description,
                            @RequestParam("company") String company,
                            @RequestParam("developerName") String developerName) {
        try {
            return s3Service.uploadFile(file, icon, name, version, description, company, developerName);
        } catch (IOException e) {
            return "File Upload Failed: " + e.getMessage();
        }
    }
}
