package com.example.files_microservice.interceptors;

import com.example.files_microservice.constants.Topics;
import com.example.files_microservice.jwt.AccessToken;
import com.example.files_microservice.jwt.AccessTokenDeserializer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.CompletableFuture;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final AccessTokenDeserializer accessTokenDeserializer;

    public LoggerInterceptor(KafkaTemplate<String, Object> kafkaTemplate,
                             AccessTokenDeserializer accessTokenDeserializer) {
        this.kafkaTemplate = kafkaTemplate;
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

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(Topics.UPLOAD_FILE, message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
        return true;
    }
}
