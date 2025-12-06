package com.ind.SmartExpenseTracker;

import com.ind.SmartExpenseTracker.service.AuthService;
import com.ind.SmartExpenseTracker.dto.LoginRequestDTO;
import com.ind.SmartExpenseTracker.dto.RegisterRequestDTO;
import com.ind.SmartExpenseTracker.entity.User;
import com.ind.SmartExpenseTracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    public AuthServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        RegisterRequestDTO req = new RegisterRequestDTO("Srajal", "srajal@example.com", "pass123", "INR");
        User user = new User();
        user.setEmail(req.getEmail());

        when(userRepository.save(any(User.class))).thenReturn(user);

        String result = authService.register(req);
        assertEquals("User registered successfully", result);
    }

    @Test
    void testLoginSuccess() {
        LoginRequestDTO req = new LoginRequestDTO("srajal@example.com", "pass123");
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPasswordHash("pass123"); // assume plain for test

        when(userRepository.findByEmail(req.getEmail())).thenReturn(Optional.of(user));

        String result = authService.login(req);
        assertEquals("Login successful", result);
    }
}

