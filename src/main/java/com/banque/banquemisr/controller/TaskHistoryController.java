package com.banque.banquemisr.controller;


import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.entity.TaskHistory;
import com.banque.banquemisr.enums.TaskStatus;
import com.banque.banquemisr.model.dto.TaskDto;
import com.banque.banquemisr.service.TaskHistoryService;
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
@RequestMapping("/api/taskHistory")
@RequiredArgsConstructor
@Slf4j
public class TaskHistoryController {
    private final TaskHistoryService taskHistoryService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<TaskHistory>> getAllTaskHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        log.info("Getting all tasks history...");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort[0]).descending());
        Page<TaskHistory> tasks = taskHistoryService.getAllTaskHistory(pageable);
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<TaskHistory>> getTaskHistoryByUser(@PathVariable String username) {
        log.info("Getting task history with username {} ", username);
        List<TaskHistory> task = taskHistoryService.getTaskHistoryByUser(username);
        return ResponseEntity.ok().body(task);
    }
}
