package com.banque.banquemisr.service;

import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.repository.TaskRepository;
import com.banque.banquemisr.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void testGetAllTasks() {
        when(taskRepository.findAll()).thenReturn(Collections.singletonList(new Task()));

        taskService.getAllTasks();

        verify(taskRepository).findAll();
    }

    // Write similar tests for other service methods
}
