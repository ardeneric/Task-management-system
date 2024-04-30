package com.banque.banquemisr.service;

import com.banque.banquemisr.entity.TaskHistory;
import com.banque.banquemisr.repository.TaskHistoryRepository;
import com.banque.banquemisr.service.impl.TaskHistoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskHistoryServiceImplTest {

    private TaskHistoryServiceImpl taskHistoryService;

    @Mock
    private TaskHistoryRepository taskHistoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskHistoryService = new TaskHistoryServiceImpl(taskHistoryRepository);
    }

    @Test
    void testCreateTaskHistory() {
        TaskHistory taskHistory = new TaskHistory();
        when(taskHistoryRepository.save(taskHistory)).thenReturn(taskHistory);

        TaskHistory result = taskHistoryService.createTaskHistory(taskHistory);

        assertEquals(taskHistory, result);
        verify(taskHistoryRepository, times(1)).save(taskHistory);
    }

    @Test
    void testGetAllTaskHistory() {
        Pageable pageable = Pageable.unpaged();
        List<TaskHistory> taskHistoryList = Collections.singletonList(new TaskHistory());
        Page<TaskHistory> page = new PageImpl<>(taskHistoryList);
        when(taskHistoryRepository.findAll(pageable)).thenReturn(page);

        Page<TaskHistory> result = taskHistoryService.getAllTaskHistory(pageable);

        assertEquals(page, result);
        verify(taskHistoryRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetTaskHistoryByUser() {
        String username = "testUser";
        List<TaskHistory> taskHistoryList = Collections.singletonList(new TaskHistory());
        when(taskHistoryRepository.findByTask_User_Username(username)).thenReturn(taskHistoryList);

        List<TaskHistory> result = taskHistoryService.getTaskHistoryByUser(username);

        assertEquals(taskHistoryList, result);
        verify(taskHistoryRepository, times(1)).findByTask_User_Username(username);
    }
}
