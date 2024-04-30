package com.banque.banquemisr.service.impl;

import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.enums.NotificationType;
import com.banque.banquemisr.enums.OperationType;
import com.banque.banquemisr.enums.TaskStatus;
import com.banque.banquemisr.event.NotificationEvent;
import com.banque.banquemisr.event.TaskEvent;
import com.banque.banquemisr.repository.TaskRepository;
import com.banque.banquemisr.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ApplicationEventPublisher eventPublisher;


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
        final Task savedTask = taskRepository.save(task);
        eventPublisher.publishEvent(new TaskEvent(this, savedTask, OperationType.CREATED));
        if(!ObjectUtils.isEmpty(task.getUser())){
            eventPublisher.publishEvent(new NotificationEvent(
                    this,
                    task.getId(),
                    task.getUser().getId(),
                    NotificationType.IMPORTANT_UPDATE,
                    String.format("Task %s has been created and assigned to you.",task.getTitle())));
        }
        return savedTask;
    }

    @Override
    public Task updateTask(Long id, Task task) {
        if (ObjectUtils.isEmpty(taskRepository.findById(id))){
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        task.setId(id);
        final Task savedTask = taskRepository.save(task);
        eventPublisher.publishEvent(new TaskEvent(this, savedTask, OperationType.UPDATED));
        if(!ObjectUtils.isEmpty(task.getUser())){
            eventPublisher.publishEvent(new NotificationEvent(
                    this,
                    task.getId(),
                    task.getUser().getId(),
                    NotificationType.IMPORTANT_UPDATE,
                    String.format("Task %s has been updated.",task.getTitle())));
        }
        return savedTask;
    }

    @Override
    public void deleteTask(Long id) {
        final Task task = taskRepository.findById(id).orElse(null);
        eventPublisher.publishEvent(new TaskEvent(this, task, OperationType.DELETED));
        if(!ObjectUtils.isEmpty(task) && !ObjectUtils.isEmpty(task.getUser())){
            eventPublisher.publishEvent(new NotificationEvent(
                    this,
                    task.getId(),
                    task.getUser().getId(),
                    NotificationType.IMPORTANT_UPDATE,
                    String.format("Task assigned to you, %s has been deleted.",task.getTitle())));
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> searchTasks(String title, String description, TaskStatus status, LocalDate dueDate) {
        return taskRepository.findByTitleContainingAndDescriptionContainingAndStatusAndDueDate(title, description, status, dueDate);
    }

    @Override
    public List<Task> getTasksDueToday(LocalDate date) {
        return taskRepository.findByDueDate(date);
    }
}
