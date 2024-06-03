package com.example.files_microservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageAuthenticator {

    private final KeycloakService keycloakService;

    public boolean verifyMessageProducer(ConsumerRecord consumerRecord) {
        return keycloakService.verifyMessageProducer(consumerRecord);
    }
}
