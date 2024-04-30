package com.banque.banquemisr.service.impl;

import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.enums.TaskStatus;
import com.banque.banquemisr.repository.TaskRepository;
import com.banque.banquemisr.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> searchTasks(String title, String description, TaskStatus status, LocalDate dueDate) {
        return taskRepository.findByTitleContainingAndDescriptionContainingAndStatusAndDueDate(title, description, status, dueDate);
    }
}
