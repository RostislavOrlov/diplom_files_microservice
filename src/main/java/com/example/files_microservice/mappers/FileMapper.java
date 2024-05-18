package com.example.files_microservice.mappers;

import com.example.files_microservice.dto.request.FileUploadRequest;
import com.example.files_microservice.dto.response.FileUploadResponse;
import com.example.files_microservice.entities.FileEntity;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public FileEntity toEntity(String id, FileUploadRequest request) {
        return FileEntity.builder()
                .id(id)
                .name(request.name())
                .build();
    }

    public FileUploadResponse toFileUploadResponse(FileEntity fileEntity) {
        return new FileUploadResponse(
                fileEntity.getId(),
                fileEntity.getName(),
                ""
        );
    }
}
