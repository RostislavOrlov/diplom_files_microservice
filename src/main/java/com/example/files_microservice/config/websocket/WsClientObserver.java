package com.example.files_microservice.config.websocket;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@AllArgsConstructor
public class WsClientObserver {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostConstruct
    public void init() {

    }

    @PreDestroy
    public void destroy() {

    }

}
