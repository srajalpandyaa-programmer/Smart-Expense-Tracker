package com.ind.SmartExpenseTracker.service;

import com.ind.SmartExpenseTracker.dto.ReportDTO;
import com.ind.SmartExpenseTracker.entity.Expense;
import com.ind.SmartExpenseTracker.entity.Report;
import com.ind.SmartExpenseTracker.repository.ExpenseRepository;
import com.ind.SmartExpenseTracker.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final ExpenseRepository expenseRepository;
    private final ReportRepository reportRepository;

    public ReportService(ExpenseRepository expenseRepository, ReportRepository reportRepository) {
        this.expenseRepository = expenseRepository;
        this.reportRepository = reportRepository;
    }

    /**
     * Generate a monthly report for a given user and period.
     * @param userId the ID of the user
     * @param period the YearMonth period (e.g., 2025-12)
     * @return ReportDTO with totals and category breakdown
     */
    public ReportDTO generateMonthlyReport(String userId, YearMonth period) {
        LocalDate start = period.atDay(1);
        LocalDate end = period.atEndOfMonth();

        // Fetch all expenses for the user in the given month
        List<Expense> expenses = expenseRepository.findByUserIdAndDateBetween(userId, start, end);

        BigDecimal total = BigDecimal.ZERO;
        Map<String, BigDecimal> byCategory = new HashMap<>();

        // Aggregate totals
        for (Expense e : expenses) {
            BigDecimal amt = e.getAmount();
            total = total.add(amt);
            byCategory.merge(e.getCategory(), amt, BigDecimal::add);
        }

        // Save or update report in DB
        Report report = reportRepository.findByUserIdAndPeriod(userId, period)
                .orElseGet(Report::new);

        report.setUserId(userId);
        report.setPeriod(period);
        report.setTotalAmount(total);
        report.setCategoryTotals(byCategory);
        reportRepository.save(report);

        // Return DTO
        return new ReportDTO(period, total, byCategory);
    }
}

