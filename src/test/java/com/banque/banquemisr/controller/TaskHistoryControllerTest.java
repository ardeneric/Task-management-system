package com.banque.banquemisr.controller;

import com.banque.banquemisr.entity.TaskHistory;
import com.banque.banquemisr.service.TaskHistoryService;
import com.banque.banquemisr.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TaskHistoryControllerTest {

    @Mock
    private TaskHistoryService taskHistoryService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskHistoryController taskHistoryController;

    @Test
    void getAllTaskHistory_ReturnsTaskHistoryPage() {
        // Mock data
        Page<TaskHistory> taskHistoryPage = new PageImpl<>(List.of(new TaskHistory(), new TaskHistory()));
        Mockito.when(taskHistoryService.getAllTaskHistory(Mockito.any(Pageable.class))).thenReturn(taskHistoryPage);

        // Call the controller method
        ResponseEntity<Page<TaskHistory>> response = taskHistoryController.getAllTaskHistory(0, 10, new String[]{"id", "asc"});

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskHistoryPage, response.getBody());
    }

    @Test
    void getTaskHistoryByUser_ReturnsTaskHistoryList() {
        List<TaskHistory> taskHistoryList = List.of(new TaskHistory(), new TaskHistory());
        Mockito.when(taskHistoryService.getTaskHistoryByUser(Mockito.anyString())).thenReturn(taskHistoryList);

        ResponseEntity<List<TaskHistory>> response = taskHistoryController.getTaskHistoryByUser("username");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskHistoryList, response.getBody());
    }

}

   
