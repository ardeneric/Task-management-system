package com.banque.banquemisr.service;

import com.banque.banquemisr.entity.Notification;
import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.entity.User;
import com.banque.banquemisr.enums.NotificationType;
import com.banque.banquemisr.repository.NotificationRepository;
import com.banque.banquemisr.repository.TaskRepository;
import com.banque.banquemisr.repository.UserRepository;
import com.banque.banquemisr.service.impl.EmailService;
import com.banque.banquemisr.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class NotificationServiceImplTest {

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNotification() {
        Long taskId = 1L;
        Long userId = 1L;
        NotificationType notificationType = NotificationType.IMPORTANT_UPDATE;
        String message = "Test Message";

        Task task = new Task();
        task.setId(taskId);

        User user = new User();
        user.setId(userId);
        user.setEmail("user@example.com");

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        notificationService.createNotification(taskId, userId, notificationType, message);

        verify(emailService, times(1)).sendEmail(user.getEmail(), notificationType.name(), message);
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }
}
