package com.banque.banquemisr.service;


import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    Page<Task> getAllTasks(Pageable pageable);

    Task getTaskById(Long id);

    Task createTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    List<Task> searchTasks(String title, String description, TaskStatus status, LocalDate dueDate);

    List<Task> getTasksDueToday(LocalDate date);
}
