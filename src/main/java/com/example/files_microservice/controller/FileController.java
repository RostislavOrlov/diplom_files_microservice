package com.example.files_microservice.controller;


import com.example.files_microservice.dto.request.FileDownloadRequest;
import com.example.files_microservice.dto.request.FileUploadRequest;
import com.example.files_microservice.dto.response.FileDownloadResponse;
import com.example.files_microservice.dto.response.FileUploadResponse;
import com.example.files_microservice.service.FileService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Validated
@RestController
@RequestMapping("/api")
public class FileController {

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(path = "/upload-file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FileUploadResponse> uploadFile(
            @Validated @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name
            ) throws ServerException, InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {

        FileUploadRequest request = new FileUploadRequest(file, name);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fileService.uploadFile(request));
    }

    @KafkaListener(topics = "files.download_image", groupId = "test-group-id") //разобраться с groupId (можно только константы)
    @PostMapping(path = "/download-file", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FileDownloadResponse> downloadFile(FileDownloadRequest request) {

        return ResponseEntity.ok()
                .build();
    }
}
