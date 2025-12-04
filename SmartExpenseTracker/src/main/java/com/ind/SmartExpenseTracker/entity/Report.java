package com.ind.SmartExpenseTracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;


@Data                       // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor          // generates no-args constructor
@AllArgsConstructor
@Document(collection = "reports")
public class Report {
    @Id
    private String id;
    @Indexed
    private String userId;
    private YearMonth period;
    private BigDecimal totalAmount;
    private Map<String, BigDecimal> categoryTotals;
    // getters/setters
}


