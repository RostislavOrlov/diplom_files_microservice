package com.example.files_microservice;

import com.example.files_microservice.config.app.WsConfigMessageBrokerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(WsConfigMessageBrokerProperties.class)
public class FilesMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesMicroserviceApplication.class, args);
    }

}
