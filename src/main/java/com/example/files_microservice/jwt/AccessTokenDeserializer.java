package com.example.files_microservice.jwt;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.UUID;
import java.util.function.Function;

@Component
public class AccessTokenDeserializer implements Function<String, AccessToken> {

    @Override
    public AccessToken apply(String s) {

        try {
            var signedJWT = SignedJWT.parse(s);
            var claimsSet = signedJWT.getJWTClaimsSet();
            return new AccessToken(
                    UUID.fromString(claimsSet.getJWTID()),
                    claimsSet.getSubject(),
                    claimsSet.getIssueTime().toInstant(),
                    claimsSet.getExpirationTime().toInstant()
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
