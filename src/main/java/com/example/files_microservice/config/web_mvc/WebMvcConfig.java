package com.example.files_microservice.config.web_mvc;

import com.example.files_microservice.interceptors.LoggerInterceptor;
import com.example.files_microservice.jwt.AccessTokenDeserializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final AccessTokenDeserializer accessTokenDeserializer;

    public WebMvcConfig(KafkaTemplate<String, Object> kafkaTemplate,
                        AccessTokenDeserializer accessTokenDeserializer) {
        this.kafkaTemplate = kafkaTemplate;
        this.accessTokenDeserializer = accessTokenDeserializer;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor(kafkaTemplate, accessTokenDeserializer));
    }
}
