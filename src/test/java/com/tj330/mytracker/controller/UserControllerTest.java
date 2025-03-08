package com.tj330.mytracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tj330.mytracker.entities.User;
import com.tj330.mytracker.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createUserNullName() throws Exception {
        User user =  User.builder()
                .name(null)
                .email(null)
                .build();

        mockMvc.perform(post("/api/v1/tracker")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserTest() throws Exception {
        User user =  User.builder()
                .name("jobit")
                .email("jjsfire@gmail.com")
                .build();

        mockMvc.perform(post("/api/v1/tracker")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }
}