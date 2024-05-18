package com.example.files_microservice.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
//                .requiresChannel(channelConfigurer ->
//                        channelConfigurer
//                                .requestMatchers(request -> request.getHeader("X-Forwarded-Proto") != null)
//                                .requiresSecure()
//                )
                .build();
    }
}
