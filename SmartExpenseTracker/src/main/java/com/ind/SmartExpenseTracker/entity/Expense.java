package com.ind.SmartExpenseTracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Expense entity represents an expense record stored in MongoDB.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expenses")
public class Expense {

    @Id
    private String id;          // MongoDB document ID

    private String userId;      // ID of the user who owns this expense
    private String category;    // e.g. Food, Travel
    private BigDecimal amount;  // Expense amount
    private String currency;    // e.g. INR, USD
    private LocalDate date;     // Date of expense
    private String note;        // Optional description
}


