package com.ind.SmartExpenseTracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "expenses")
public class Expense {
    @Id
    private String id;
    @Indexed
    private String userId;
    private String category;
    private BigDecimal amount;
    private String currency;
    private LocalDate date;
    private String note;
    // getters/setters
}

