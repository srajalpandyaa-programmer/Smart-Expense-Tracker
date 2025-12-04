package com.ind.SmartExpenseTracker.controller;

import com.ind.SmartExpenseTracker.dto.LoginRequestDTO;
import com.ind.SmartExpenseTracker.dto.RegisterRequestDTO;
import com.ind.SmartExpenseTracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service) { this.service = service; }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO req) {
        return ResponseEntity.ok(service.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO req) {
        return ResponseEntity.ok(service.login(req));
    }
}
