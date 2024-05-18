package com.example.files_microservice.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Data
public class AccessToken {

    UUID tokenId;

    String subject;

    Instant issuedAt;

    Instant expiresAt;

}
