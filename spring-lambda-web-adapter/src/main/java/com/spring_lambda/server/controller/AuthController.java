package com.spring_lambda.server.controller;

import com.spring_lambda.server.dto.AuthRequest;
import com.spring_lambda.server.dto.AuthVerifyRequest;
import com.spring_lambda.server.dto.JwtResponse;
import com.spring_lambda.server.exception.InvalidTokenException;
import com.spring_lambda.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/request")
    public ResponseEntity<String> requestLogin(@RequestBody AuthRequest request) {
        authService.sendAuthCode(request.getIdentifier());
        return ResponseEntity.ok("A login link has been sent to your identifier.");
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyLogin(@RequestBody AuthVerifyRequest request) {
        try {
            String jwtToken = authService.verifyAndGenerateToken(request.getIdentifier(), request.getToken());
            return ResponseEntity.ok(new JwtResponse(jwtToken));
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(401).body("User or token invalid.");
        }
    }
}