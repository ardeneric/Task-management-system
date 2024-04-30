package com.banque.banquemisr.controller;

import com.banque.banquemisr.entity.Notification;
import com.banque.banquemisr.enums.NotificationType;
import com.banque.banquemisr.model.dto.NotificationDto;
import com.banque.banquemisr.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @InjectMocks
    private NotificationController notificationController;

    @Mock
    private NotificationService notificationService;

    public NotificationControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNotifications() {
        List<Notification> expectedNotifications = Collections.singletonList(new Notification());
        when(notificationService.getAllNotifications()).thenReturn(expectedNotifications);

        ResponseEntity<List<Notification>> responseEntity = notificationController.getAllNotifications();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedNotifications, responseEntity.getBody());
    }

    @Test
    void testSendNotification() {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setTaskId(1L);
        notificationDto.setUserId(1L);
        notificationDto.setNotificationType(NotificationType.IMPORTANT_UPDATE);
        notificationDto.setMessage("Test Message");

        Notification expectedNotification = new Notification();
        when(notificationService.createNotification(notificationDto.getTaskId(), notificationDto.getUserId(),
                notificationDto.getNotificationType(), notificationDto.getMessage())).thenReturn(expectedNotification);

        ResponseEntity<Notification> responseEntity = notificationController.sendNotification(notificationDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedNotification, responseEntity.getBody());
    }
}
