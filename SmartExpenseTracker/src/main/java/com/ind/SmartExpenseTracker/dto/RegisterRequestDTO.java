package com.ind.SmartExpenseTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * RegisterRequest DTO represents the payload
 * when a new user registers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank
    private String displayName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String preferredCurrency; // optional
}

