package com.banque.banquemisr.controller;


import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.enums.TaskStatus;
import com.banque.banquemisr.model.dto.TaskDto;
import com.banque.banquemisr.service.TaskService;
import com.banque.banquemisr.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        log.info("Getting all tasks...");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort[0]).descending());
        Page<Task> tasks = taskService.getAllTasks(pageable);
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        log.info("Getting task with id {} ", id);
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Task> createTask(@RequestBody @Valid TaskDto taskDto) {
        log.info("Creating task {} ", taskDto);
        final Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .priority(taskDto.getPriority())
                .status(taskDto.getStatus())
                .dueDate(taskDto.getDueDate())
                .user(StringUtils.isEmpty(taskDto.getUsername()) ? null : userService.getUserByUsername(taskDto.getUsername()))
                .build();
        Task createdTask = taskService.createTask(task);
        if (createdTask == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        log.info("Updating task :: {} ", taskDto);
        final Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .priority(taskDto.getPriority())
                .dueDate(taskDto.getDueDate())
                .user(StringUtils.isEmpty(taskDto.getUsername()) ? null : userService.getUserByUsername(taskDto.getUsername()))
                .build();
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok().body(updatedTask);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.info("Deleting task with id :: {} ", id);
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String description,
                                                  @RequestParam(required = false) TaskStatus status,
                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        log.info("Searching for tasks with title: {}, description: {}, status: {}, dueDate: {}", title, description, status, dueDate);
        List<Task> tasks = taskService.searchTasks(title, description, status, dueDate);
        return ResponseEntity.ok().body(tasks);
    }
}
