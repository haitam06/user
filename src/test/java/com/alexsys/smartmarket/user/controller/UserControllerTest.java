package com.alexsys.smartmarket.user.controller;

import com.alexsys.smartmarket.user.model.User;
import com.alexsys.smartmarket.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("johndoe");
        testUser.setEmail("johndoe@example.com");
        testUser.setPassword("password123");
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() throws Exception {
        User anotherUser = new User();
        anotherUser.setId(2);
        anotherUser.setUsername("janedoe");
        anotherUser.setEmail("janedoe@example.com");
        anotherUser.setPassword("pass");

        List<User> users = Arrays.asList(testUser, anotherUser);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/smartmarket/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].username", is("johndoe")))
                .andExpect(jsonPath("$[1].username", is("janedoe")));
    }

    @Test
    void getUserById_shouldReturnUser() throws Exception {
        when(userService.getUserById(1)).thenReturn(Optional.of(testUser));

        mockMvc.perform(get("/smartmarket/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("johndoe")))
                .andExpect(jsonPath("$.email", is("johndoe@example.com")));
    }

    @Test
    void getUserById_shouldReturnNotFound() throws Exception {
        when(userService.getUserById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/smartmarket/users/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_shouldReturnCreatedUser() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/smartmarket/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("johndoe")))
                .andExpect(jsonPath("$.email", is("johndoe@example.com")));
    }

    @Test
    void updateUser_shouldReturnUpdatedUser() throws Exception {
        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setUsername("newname");
        updatedUser.setEmail("newemail@example.com");

        when(userService.updateUser(Mockito.eq(1), any(User.class)))
                .thenReturn(Optional.of(updatedUser));

        mockMvc.perform(put("/smartmarket/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("newname")))
                .andExpect(jsonPath("$.email", is("newemail@example.com")));
    }

    @Test
    void updateUser_shouldReturnNotFound() throws Exception {
        when(userService.updateUser(Mockito.eq(99), any(User.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/smartmarket/users/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUser_shouldReturnNoContent() throws Exception {
        doNothing().when(userService).deleteUser(1);

        mockMvc.perform(delete("/smartmarket/users/1"))
                .andExpect(status().isNoContent());
    }
}
