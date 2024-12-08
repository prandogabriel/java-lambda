package com.spring_lambda.server.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthRepository {

    private final Map<String, String> tokenStore = new HashMap<>();

    public void saveToken(String identifier, String token) {
        tokenStore.put(identifier, token);
    }

    public String getToken(String identifier) {
        return tokenStore.get(identifier);
    }
}