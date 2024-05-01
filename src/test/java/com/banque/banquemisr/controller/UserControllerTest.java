package com.banque.banquemisr.controller;

import com.banque.banquemisr.entity.User;
import com.banque.banquemisr.enums.UserRole;
import com.banque.banquemisr.model.dto.CreateUserDto;
import com.banque.banquemisr.service.UserService;
import com.banque.banquemisr.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @WithMockUser(roles = "ADMIN")
    void addUser_ShouldReturnCreatedStatus() throws Exception {
        // Arrange
        CreateUserDto request = new CreateUserDto();
        request.setUsername("testuser");
        request.setPassword("testpassword");
        request.setRole(UserRole.USER);
        request.setEmail("test@example.com");

        User savedUser = new User();
        savedUser.setUsername(request.getUsername());
        savedUser.setPassword(request.getPassword()); // Password will be encrypted in the service
        savedUser.setRole(request.getRole());
        savedUser.setEmail(request.getEmail());

        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(savedUser);


        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(request.getUsername()))
                .andExpect(jsonPath("$.password").doesNotExist()) // Password should not be returned
                .andExpect(jsonPath("$.role").value(request.getRole().toString()))
                .andExpect(jsonPath("$.email").value(request.getEmail()));

        verify(userService, times(1)).createUser(any(User.class));
    }
}
