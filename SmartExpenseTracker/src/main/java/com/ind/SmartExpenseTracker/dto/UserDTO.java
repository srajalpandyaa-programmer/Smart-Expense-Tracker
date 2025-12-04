package com.ind.SmartExpenseTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor          // generates no-args constructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private String displayName;
    private String preferredCurrency;

}

