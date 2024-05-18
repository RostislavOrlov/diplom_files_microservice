package com.example.files_microservice.repository;

import com.example.files_microservice.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilePostgresRepository extends JpaRepository<FileEntity, String> {
}
