package com.tj330.mytracker.services;

import com.tj330.mytracker.controller.NotFoundException;
import com.tj330.mytracker.entities.Expense;
import com.tj330.mytracker.entities.User;
import com.tj330.mytracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public User saveNewUser(User user) {
        userRepository.save(user);
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {

        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<Expense> findExpenses(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        return user.get().getExpenses();
    }

    @Override
    public Optional<User> updateUserById(UUID uuid, User user) {
        return userRepository.findById(uuid)
                .map(foundUser -> {
                    foundUser.setName(user.getName());
                    foundUser.setEmail(user.getEmail());
                    return userRepository.save(foundUser);
                });
    }

    @Override
    public Boolean deleteUserById(UUID uuid) {
        if (userRepository.existsById(uuid)) {
            userRepository.deleteById(uuid);
            return true;
        }
        return false;
    }
}
