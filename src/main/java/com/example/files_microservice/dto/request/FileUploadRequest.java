package com.example.files_microservice.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record FileUploadRequest(
        MultipartFile file,
        String name
) {
}
