package com.spring_lambda.server.service;

import com.spring_lambda.server.exception.InvalidTokenException;
import com.spring_lambda.server.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    private final String secretKey = "70abceeb20d82fc2d55e8934d1ad05ad17609752";
    private final long expirationTimeMs = 3600000; // 1 hora

    public void sendAuthCode(String identifier) {
        String token = UUID.randomUUID().toString();
        authRepository.saveToken(identifier, token);

        RestTemplate restTemplate = new RestTemplate();
        String payload = String.format("Your authentication token is: %s", token);
        restTemplate.postForEntity("https://webhook.site/5afcf2b0-eac9-4744-8239-729defd0a030", payload, String.class);
    }

    public String verifyAndGenerateToken(String identifier, String token) throws InvalidTokenException {
        String storedToken = authRepository.getToken(identifier);
        if (storedToken == null || !storedToken.equals(token)) {
            throw new InvalidTokenException("Invalid or expired token.");
        }

        // Gerar JWT
        return Jwts.builder()
                .setSubject(identifier)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}