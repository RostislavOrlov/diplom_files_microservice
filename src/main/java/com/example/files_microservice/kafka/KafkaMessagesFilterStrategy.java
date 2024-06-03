package com.example.files_microservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessagesFilterStrategy implements RecordFilterStrategy {

    private final KafkaMessageAuthenticator authenticator;

    public KafkaMessagesFilterStrategy(KafkaMessageAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public boolean filter(ConsumerRecord consumerRecord) {
        return authenticator.verifyMessageProducer(consumerRecord);
    }
}
