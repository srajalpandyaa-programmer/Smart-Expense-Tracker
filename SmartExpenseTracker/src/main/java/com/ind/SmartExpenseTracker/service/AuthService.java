package com.ind.SmartExpenseTracker.service;

import com.ind.SmartExpenseTracker.configuration.JwtUtil;
import com.ind.SmartExpenseTracker.dto.LoginRequestDTO;
import com.ind.SmartExpenseTracker.dto.RegisterRequestDTO;
import com.ind.SmartExpenseTracker.entity.User;
import com.ind.SmartExpenseTracker.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository repo, BCryptPasswordEncoder encoder, JwtUtil jwtUtil) {
        this.repo = repo; this.encoder = encoder; this.jwtUtil = jwtUtil;
    }

    public String register(@Valid RegisterRequestDTO req) {
        if (repo.findByEmail(req.getEmail()).isPresent()) throw new IllegalArgumentException("Email exists");
        User u = new User();
        u.setEmail(req.getEmail());
        u.setPasswordHash(encoder.encode(req.getPassword()));
        u.setDisplayName(req.getDisplayName());
        u.setPreferredCurrency(req.getPreferredCurrency());
        repo.save(u);
        return jwtUtil.generateToken(u.getId(), u.getEmail());
    }

    public String login(@Valid LoginRequestDTO req) {
        User u = repo.findByEmail(req.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!encoder.matches(req.getPassword(), u.getPasswordHash())) throw new IllegalArgumentException("Invalid credentials");
        return jwtUtil.generateToken(u.getId(), u.getEmail());
    }
}

