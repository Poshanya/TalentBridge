package com.talentbridge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talentbridge.dto.LoginRequestDTO;
import com.talentbridge.entity.User;
import com.talentbridge.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @Valid @RequestBody LoginRequestDTO request) {

        User user = authService.login(request);

        return ResponseEntity.ok(user);
    }
}