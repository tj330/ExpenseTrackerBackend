package com.tj330.mytracker.services;

import com.tj330.mytracker.entities.Expense;
import com.tj330.mytracker.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;



@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Override
    public Expense saveNewExpense(Expense expense) {

        return expenseRepository.save(expense);
    }

    @Override
    public Optional<Expense> updateById(UUID uuid, Expense expense) {
        return expenseRepository.findById(uuid)
                .map(foundExpense -> {
                    foundExpense.setUuid(uuid);
                    foundExpense.setCategory(expense.getCategory());
                    foundExpense.setDescription(expense.getDescription());
                    foundExpense.setAmount(expense.getAmount());
                    return expenseRepository.save(foundExpense);
                });
    }

    @Override
    public Boolean deleteById(UUID uuid) {
        if (expenseRepository.existsById(uuid)) {
            expenseRepository.deleteById(uuid);
            return true;
        }
        return false;
    }
}
