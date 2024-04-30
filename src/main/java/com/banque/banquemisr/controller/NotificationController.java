package com.banque.banquemisr.controller;


import com.banque.banquemisr.entity.Notification;
import com.banque.banquemisr.model.dto.NotificationDto;
import com.banque.banquemisr.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok().body(notifications);
    }

    @PostMapping
    public ResponseEntity<Notification> sendNotification(@RequestBody NotificationDto notificationDto) {
        Notification notification = notificationService.createNotification(
                notificationDto.getTaskId(),
                notificationDto.getUserId(),
                notificationDto.getNotificationType(),
                notificationDto.getMessage()
        );
        if (notification == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }
}
