package com.ind.SmartExpenseTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data                       // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor          // generates no-args constructor
@AllArgsConstructor
public class ExpenseDTO {
    private String id;
    private String category;
    private BigDecimal amount;
    private String currency;
    private LocalDate date;
    private String note;
    // constructors, getters/setters
}

