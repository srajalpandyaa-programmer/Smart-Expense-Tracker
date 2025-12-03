package com.ind.SmartExpenseTracker.service;

import com.ind.SmartExpenseTracker.dto.ExpenseDTO;
import com.ind.SmartExpenseTracker.entity.Expense;
import com.ind.SmartExpenseTracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository repo;
    public ExpenseService(ExpenseRepository repo) { this.repo = repo; }

    public ExpenseDTO addExpense(String userId, ExpenseRequest req) {
        Expense e = new Expense();
        e.setUserId(userId);
        e.setCategory(req.getCategory());
        e.setAmount(req.getAmount());
        e.setCurrency(req.getCurrency());
        e.setDate(req.getDate());
        e.setNote(req.getNote());
        return toDTO(repo.save(e));
    }

    public List<ExpenseDTO> listExpenses(String userId) {
        return repo.findByUserIdOrderByDateDesc(userId).stream().map(this::toDTO).toList();
    }

    private ExpenseDTO toDTO(Expense e) {
        return new ExpenseDTO(e.getId(), e.getCategory(), e.getAmount(), e.getCurrency(), e.getDate(), e.getNote());
    }
}

