package com.banque.banquemisr.controller;

import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.enums.TaskPriority;
import com.banque.banquemisr.enums.TaskStatus;
import com.banque.banquemisr.model.dto.TaskDto;
import com.banque.banquemisr.service.TaskService;
import com.banque.banquemisr.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class TaskControllerTest {

    @MockBean
    private TaskService taskService;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllTasks() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");

        Pageable pageable = PageRequest.of(0, 10);

        Page<Task> page = new PageImpl<>(Collections.singletonList(task), pageable, 1);
        Mockito.when(taskService.getAllTasks(pageable)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetTaskById() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description");
        task.setPriority(TaskPriority.LOW);
        task.setStatus(TaskStatus.TODO);
        task.setDueDate(LocalDate.now());

        when(taskService.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Task 1"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Task 1");
        taskDto.setDescription("Description");
        taskDto.setPriority(TaskPriority.LOW);
        taskDto.setStatus(TaskStatus.TODO);
        taskDto.setDueDate(LocalDate.now());

        Task task = new Task();
        task.setId(1L);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());

        when(taskService.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Task 1\",\"description\":\"Description\",\"priority\":\"LOW\",\"status\":\"TODO\",\"dueDate\":\"2024-04-30\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Task 1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Task 1 Updated");
        taskDto.setDescription("Description Updated");
        taskDto.setPriority(TaskPriority.HIGH);
        taskDto.setStatus(TaskStatus.IN_PROGRESS);
        taskDto.setDueDate(LocalDate.now());

        Task task = new Task();
        task.setId(1L);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());

        when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Task 1 Updated\",\"description\":\"Description Updated\",\"priority\":\"HIGH\",\"status\":\"IN_PROGRESS\",\"dueDate\":\"2024-04-30\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Task 1 Updated"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testSearchTasks() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description");
        task.setStatus(TaskStatus.TODO);
        task.setDueDate(LocalDate.now());

        List<Task> tasks = Collections.singletonList(task);

        when(taskService.searchTasks("title", "description", TaskStatus.TODO, LocalDate.now()))
                .thenReturn(tasks);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/search")
                        .param("title", "title")
                        .param("description", "description")
                        .param("status", TaskStatus.TODO.toString())
                        .param("dueDate", LocalDate.now().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Task 1"));
    }
}
