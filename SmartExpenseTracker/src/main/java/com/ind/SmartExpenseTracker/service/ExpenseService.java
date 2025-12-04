package com.ind.SmartExpenseTracker.service;

import com.ind.SmartExpenseTracker.dto.ExpenseDTO;
import com.ind.SmartExpenseTracker.entity.Expense;
import com.ind.SmartExpenseTracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for managing expenses.
 * Handles business logic and maps between DTOs and entities.
 */
@Service
public class ExpenseService {

    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    /**
     * Add a new expense for a given user.
     *
     * @param userId the ID of the user
     * @param req    the expense details (DTO)
     * @return the saved expense as DTO
     */
    public ExpenseDTO addExpense(String userId, ExpenseDTO req) {
        Expense e = new Expense();
        e.setUserId(userId);
        e.setCategory(req.getCategory());
        e.setAmount(req.getAmount());
        e.setCurrency(req.getCurrency());
        e.setDate(req.getDate());
        e.setNote(req.getNote());
        return toDTO(repo.save(e));
    }

    /**
     * List all expenses for a user, ordered by date descending.
     *
     * @param userId the ID of the user
     * @return list of expenses as DTOs
     */
    public List<ExpenseDTO> listExpenses(String userId) {
        return repo.findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    /**
     * Convert an Expense entity to ExpenseDTO.
     */
    private ExpenseDTO toDTO(Expense e) {
        return new ExpenseDTO(
                e.getId(),
                e.getCategory(),
                e.getAmount(),
                e.getCurrency(),
                e.getDate(),
                e.getNote()
        );
    }
}
