package com.banque.banquemisr.service;

import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.enums.TaskStatus;
import com.banque.banquemisr.repository.TaskRepository;
import com.banque.banquemisr.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void testGetAllTasks() {
        List<Task> tasks = Collections.singletonList(new Task());
        Page<Task> page = new PageImpl<>(tasks);
        when(taskRepository.findAll(any(Pageable.class))).thenReturn(page);

        Pageable pageable = PageRequest.of(0, 10);

        List<Task> result = taskService.getAllTasks(pageable).getContent();

        verify(taskRepository).findAll(any(Pageable.class));

        assertEquals(tasks, result);
    }

    @Test
    void testSearchTasks() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description");
        task.setStatus(TaskStatus.TODO);
        task.setDueDate(LocalDate.now());

        when(taskRepository.findByTitleContainingAndDescriptionContainingAndStatusAndDueDate(any(), any(), any(), any()))
                .thenReturn(Collections.singletonList(task));

        List<Task> foundTasks = taskService.searchTasks("Task", "Description", TaskStatus.TODO, LocalDate.now());

        assertEquals(1, foundTasks.size());
        assertEquals(task.getId(), foundTasks.get(0).getId());
        assertEquals(task.getTitle(), foundTasks.get(0).getTitle());
        assertEquals(task.getDescription(), foundTasks.get(0).getDescription());
        assertEquals(task.getStatus(), foundTasks.get(0).getStatus());
        assertEquals(task.getDueDate(), foundTasks.get(0).getDueDate());
    }
}
