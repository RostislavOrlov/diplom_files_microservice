package com.example.files_microservice.config.app;


import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.rabbitmq")
@AllArgsConstructor
public class WsConfigMessageBrokerProperties {

    public String host;

    public int port;

    public String clientLogin;

    public String clientPasscode;

    public String systemLogin;

    public String systemPasscode;

}
