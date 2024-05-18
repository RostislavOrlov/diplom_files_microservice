package com.example.files_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FilesMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesMicroserviceApplication.class, args);
    }

}
