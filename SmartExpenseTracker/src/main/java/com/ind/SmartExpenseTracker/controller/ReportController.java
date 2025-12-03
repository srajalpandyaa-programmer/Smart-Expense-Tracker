package com.ind.SmartExpenseTracker.controller;

import com.ind.SmartExpenseTracker.dto.ReportDTO;
import com.ind.SmartExpenseTracker.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    /**
     * Generate a monthly report for the authenticated user.
     * Accepts a YearMonth string in the request body, e.g. "2025-12".
     */
    @PostMapping("/monthly")
    public ResponseEntity<ReportDTO> monthly(@RequestBody String period, Authentication auth) {
        String userId = (String) auth.getPrincipal();
        YearMonth yearMonth = YearMonth.parse(period); // parse "YYYY-MM"
        return ResponseEntity.ok(service.generateMonthlyReport(userId, yearMonth));
    }
}


