package com.ind.SmartExpenseTracker.controller;

import com.ind.SmartExpenseTracker.dto.ExpenseRequest;
import com.ind.SmartExpenseTracker.dto.ExpenseDTO;
import com.ind.SmartExpenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService service;
    public ExpenseController(ExpenseService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ExpenseDTO> add(@Valid @RequestBody ExpenseRequest req, Authentication auth) {
        String userId = (String) auth.getPrincipal();
        return ResponseEntity.ok(service.addExpense(userId, req));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> list(Authentication auth) {
        String userId = (String) auth.getPrincipal();
        return ResponseEntity.ok(service.listExpenses(userId));
    }
}

