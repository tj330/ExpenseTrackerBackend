package com.tj330.mytracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tj330.mytracker.entities.Expense;
import com.tj330.mytracker.entities.User;
import com.tj330.mytracker.repositories.UserRepository;
import com.tj330.mytracker.services.ExpenseService;
import com.tj330.mytracker.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {
    @MockitoBean
    ExpenseService expenseService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;


    @Test
    public void createNewExpense() throws Exception {
        User user =  User.builder()
                .name("jobit")
                .email("jjsfire@gmail.com")
                .build();

        Expense expense = Expense.builder()
                .category("tech")
                .description("bought a new laptop")
                .amount(new BigDecimal("100000"))
                .date(LocalDateTime.now())
                .user(userService.findUserByEmail("jjsfire@gmail.com").get())
                .build();

        System.out.println(expense.toString());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tracker/expense")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isCreated());
    }

}