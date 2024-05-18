package com.example.files_microservice.service;

import com.example.files_microservice.repository.FilePostgresRepository;
import com.example.files_microservice.dto.request.FileUploadRequest;
import com.example.files_microservice.mappers.FileMapper;
import com.example.files_microservice.repository.FileRepository;
import com.example.files_microservice.dto.response.FileUploadResponse;
import com.example.files_microservice.interfaces.FileServiceInterface;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class FileService implements FileServiceInterface {

    private final FileRepository fileRepository;

    private final FilePostgresRepository filePostgresRepository;

    private final FileMapper fileMapper;

    public FileService(FileRepository fileRepository,
                       FilePostgresRepository filePostgresRepository,
                       FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.filePostgresRepository = filePostgresRepository;
        this.fileMapper = fileMapper;
    }

    @Transactional
    public FileUploadResponse uploadFile(FileUploadRequest request) throws ServerException, InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {

        var fileEntity = fileMapper.toEntity(UUID.randomUUID().toString(), request);

        filePostgresRepository.save(fileEntity);

        fileRepository.save(request.file());

        return fileMapper.toFileUploadResponse(fileEntity);
    }
}
