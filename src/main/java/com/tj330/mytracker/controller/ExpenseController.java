package com.tj330.mytracker.controller;

import com.tj330.mytracker.entities.Expense;
import com.tj330.mytracker.entities.User;
import com.tj330.mytracker.services.ExpenseService;
import com.tj330.mytracker.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tracker/expense")
@RequiredArgsConstructor
@Slf4j
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;


    @GetMapping
    public List<Expense> getExpenses(@RequestParam String email) {
        return userService.findExpenses(email);
    }
    
    //    localhost:8080/api/v1/tracker/expense?email=varun@gmail.com
    @PostMapping
    public ResponseEntity saveExpense(@RequestBody Expense expense, @RequestParam String email) {
        Optional<User> user = userService.findUserByEmail(email);

        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        expense.setUser(user.get());
        expenseService.saveNewExpense(expense);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateExpense(@RequestParam UUID uuid, @RequestBody Expense expense) {
        if (expenseService.updateById(uuid,expense).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteExpense(@RequestParam UUID uuid) {
        if (expenseService.deleteById(uuid)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        throw new NotFoundException();
    }
}
