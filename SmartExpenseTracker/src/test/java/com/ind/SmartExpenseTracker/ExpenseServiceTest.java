package com.ind.SmartExpenseTracker;

import com.ind.SmartExpenseTracker.dto.ExpenseDTO;
import com.ind.SmartExpenseTracker.entity.Expense;
import com.ind.SmartExpenseTracker.repository.ExpenseRepository;
import com.ind.SmartExpenseTracker.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddExpense() {
        ExpenseDTO dto = new ExpenseDTO(
                null,
                "Food",
                BigDecimal.valueOf(250),
                "INR",
                LocalDate.of(2025, 12, 4),
                "Lunch with friends"
        );

        Expense savedExpense = new Expense();
        savedExpense.setId("exp123");
        savedExpense.setUserId("user123");
        savedExpense.setCategory(dto.getCategory());
        savedExpense.setAmount(dto.getAmount());
        savedExpense.setCurrency(dto.getCurrency());
        savedExpense.setDate(dto.getDate());
        savedExpense.setNote(dto.getNote());

        when(expenseRepository.save(any(Expense.class))).thenReturn(savedExpense);

        ExpenseDTO result = expenseService.addExpense("user123", dto);

        assertEquals("Food", result.getCategory());
        assertEquals(BigDecimal.valueOf(250), result.getAmount());
        assertEquals("INR", result.getCurrency());
        assertEquals("Lunch with friends", result.getNote());
    }

    @Test
    void testListExpenses() {
        Expense e1 = new Expense("exp1", "user123", "Food", BigDecimal.valueOf(100), "INR", LocalDate.now(), "Note1");
        Expense e2 = new Expense("exp2", "user123", "Travel", BigDecimal.valueOf(200), "INR", LocalDate.now(), "Note2");

        when(expenseRepository.findByUserIdOrderByDateDesc("user123"))
                .thenReturn(Arrays.asList(e1, e2));

        List<ExpenseDTO> result = expenseService.listExpenses("user123");

        assertEquals(2, result.size());
        assertEquals("Food", result.get(0).getCategory());
        assertEquals("Travel", result.get(1).getCategory());
    }
}

