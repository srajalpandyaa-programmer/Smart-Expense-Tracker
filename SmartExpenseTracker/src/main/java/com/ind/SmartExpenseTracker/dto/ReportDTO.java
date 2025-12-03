package com.ind.SmartExpenseTracker.dto;

import org.springframework.data.mongodb.core.aggregation.VariableOperators;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ReportDTO {
    private YearMonth period;
    private BigDecimal totalAmount;
    private VariableOperators.Map<String, BigDecimal> categoryTotals;
    // constructors, getters/setters
}
