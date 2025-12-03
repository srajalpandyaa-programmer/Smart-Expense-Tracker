package com.ind.SmartExpenseTracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true) private String email;
    private String passwordHash;
    private String displayName;
    private String preferredCurrency;
    // getters/setters
}
