package com.example.files_microservice.interceptors;

import com.example.files_microservice.kafka.KafkaProducerWrapper;
import com.example.files_microservice.constants.KafkaTopics;
import com.example.files_microservice.jwt.AccessToken;
import com.example.files_microservice.jwt.AccessTokenDeserializer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private final KafkaProducerWrapper kafkaProducerWrapper;

    private final AccessTokenDeserializer accessTokenDeserializer;

    public LoggerInterceptor(KafkaProducerWrapper kafkaProducerWrapper,
                             AccessTokenDeserializer accessTokenDeserializer) {
        this.kafkaProducerWrapper = kafkaProducerWrapper;
        this.accessTokenDeserializer = accessTokenDeserializer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String accessTokenString = authHeader.split(" ")[1];
        AccessToken accessToken = accessTokenDeserializer.apply(accessTokenString);
        int userId = Integer.parseInt(accessToken.getSubject());
        var message = System.out.printf("Пользователь %d совершил запрос", userId);

        kafkaProducerWrapper.sendMessage(KafkaTopics.UPLOAD_FILE, message);

        return true;
    }
}
