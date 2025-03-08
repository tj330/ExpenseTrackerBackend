package com.tj330.mytracker.services;

import com.tj330.mytracker.entities.Expense;
import com.tj330.mytracker.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseService {
    Expense saveNewExpense(Expense expense);

    Optional<Expense> updateById(UUID uuid, Expense expense);

    Boolean deleteById(UUID uuid);
}
