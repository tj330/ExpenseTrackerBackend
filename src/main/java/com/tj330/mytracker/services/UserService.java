package com.tj330.mytracker.services;


import com.tj330.mytracker.entities.Expense;
import com.tj330.mytracker.entities.User;
import jakarta.validation.Valid;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User saveNewUser(User user);

    List<User> getUsers();

    Optional<User> findUserByEmail(String email);

    List<Expense> findExpenses(String email);

    Optional<User> updateUserById(UUID uuid, @Valid User user);

    Boolean deleteUserById(UUID uuid);
}
