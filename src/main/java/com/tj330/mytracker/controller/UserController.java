package com.tj330.mytracker.controller;

import com.tj330.mytracker.entities.User;
import com.tj330.mytracker.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tracker")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> listUsers() {
       return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        userService.saveNewUser(user);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestParam UUID uuid, @Valid @RequestBody User user) {
        if (userService.updateUserById(uuid,user).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam UUID uuid) {
        if (userService.deleteUserById(uuid)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        throw new NotFoundException();
    }
}
