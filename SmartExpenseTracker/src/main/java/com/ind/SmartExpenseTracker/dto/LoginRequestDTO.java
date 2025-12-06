package com.ind.SmartExpenseTracker.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;

/**
 * LoginRequest DTO represents the payload
 * when a user attempts to log in.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    private String email;     // or username, depending on your design

    @NotBlank
    private String password;
}

