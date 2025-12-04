package com.ind.SmartExpenseTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

/**
 * ReportDTO is a Data Transfer Object used to represent
 * aggregated expense reports for a given period.
 */
@Data                       // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor          // generates no-args constructor
@AllArgsConstructor         // generates all-args constructor
public class ReportDTO {

    private YearMonth period;                  // e.g. 2025-12
    private BigDecimal totalAmount;            // total expenses in the period
    private Map<String, BigDecimal> categoryTotals; // category-wise totals
}

