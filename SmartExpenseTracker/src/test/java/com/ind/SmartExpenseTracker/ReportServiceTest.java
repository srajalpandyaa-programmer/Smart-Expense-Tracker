package com.ind.SmartExpenseTracker;

import com.ind.SmartExpenseTracker.dto.ReportDTO;
import com.ind.SmartExpenseTracker.entity.Expense;
import com.ind.SmartExpenseTracker.entity.Report;
import com.ind.SmartExpenseTracker.repository.ExpenseRepository;
import com.ind.SmartExpenseTracker.repository.ReportRepository;
import com.ind.SmartExpenseTracker.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateMonthlyReport_NewReport() {
        // Arrange
        Expense expense = new Expense();
        expense.setId("exp1");
        expense.setUserId("user123");
        expense.setCategory("Food");
        expense.setAmount(BigDecimal.valueOf(100));
        expense.setCurrency("INR");
        expense.setDate(LocalDate.of(2025, 12, 1));
        expense.setNote("Lunch");

        when(expenseRepository.findByUserIdAndDateBetween(
                eq("user123"),
                any(LocalDate.class),
                any(LocalDate.class)))
                .thenReturn(Collections.singletonList(expense));

        when(reportRepository.findByUserIdAndPeriod(eq("user123"), any(YearMonth.class)))
                .thenReturn(Optional.empty());

        Report savedReport = new Report();
        savedReport.setId("rep1");
        savedReport.setUserId("user123");
        savedReport.setPeriod(YearMonth.of(2025, 12));
        savedReport.setTotalAmount(BigDecimal.valueOf(100));
        savedReport.setCategoryTotals(Collections.singletonMap("Food", BigDecimal.valueOf(100)));

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        // Act
        ReportDTO dto = reportService.generateMonthlyReport("user123", YearMonth.of(2025, 12));

        // Assert
        assertEquals(BigDecimal.valueOf(100), dto.getTotalAmount());
        assertEquals(BigDecimal.valueOf(100), dto.getCategoryTotals().get("Food"));
    }

    @Test
    void testGenerateMonthlyReport_ExistingReport() {
        // Arrange
        Report existingReport = new Report();
        existingReport.setId("rep2");
        existingReport.setUserId("user123");
        existingReport.setPeriod(YearMonth.of(2025, 12));
        existingReport.setTotalAmount(BigDecimal.valueOf(300));
        existingReport.setCategoryTotals(Collections.singletonMap("Travel", BigDecimal.valueOf(300)));

        when(reportRepository.findByUserIdAndPeriod("user123", YearMonth.of(2025, 12)))
                .thenReturn(Optional.of(existingReport));

        // Act
        ReportDTO dto = reportService.generateMonthlyReport("user123", YearMonth.of(2025, 12));

        // Assert
        assertEquals(BigDecimal.valueOf(300), dto.getTotalAmount());
        assertEquals(BigDecimal.valueOf(300), dto.getCategoryTotals().get("Travel"));
    }
}

