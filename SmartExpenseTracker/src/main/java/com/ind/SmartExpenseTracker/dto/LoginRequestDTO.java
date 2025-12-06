package com.ind.SmartExpenseTracker.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    private String email;   

    @NotBlank
    private String password;
}

