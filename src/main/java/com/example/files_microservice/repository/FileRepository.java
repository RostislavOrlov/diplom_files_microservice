package com.example.files_microservice.repository;

import com.example.files_microservice.interfaces.FileRepositoryInterface;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Repository
public class FileRepository implements FileRepositoryInterface {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    String bucketName;

    public FileRepository(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void save(MultipartFile file) throws
            ServerException, InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        {
            UploadObjectArgs args = UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .build();

            ObjectWriteResponse response = minioClient.uploadObject(args);

        }
    }
}
