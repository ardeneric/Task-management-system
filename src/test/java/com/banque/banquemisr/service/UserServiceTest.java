package com.banque.banquemisr.service;

import com.banque.banquemisr.entity.User;
import com.banque.banquemisr.enums.UserRole;
import com.banque.banquemisr.repository.UserRepository;
import com.banque.banquemisr.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveUser_ShouldEncryptPasswordAndSaveUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setRole(UserRole.USER);
        user.setEmail("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.createUser(user);

        assertEquals(savedUser.getUsername(),user.getUsername());
        assertEquals(savedUser.getRole(),user.getRole());
        assertEquals(savedUser.getEmail(),user.getEmail());

        verify(userRepository, times(1)).save(user);
    }
}
