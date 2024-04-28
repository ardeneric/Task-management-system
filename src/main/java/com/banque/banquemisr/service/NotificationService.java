package com.banque.banquemisr.service;

import com.banque.banquemisr.entity.Notification;
import com.banque.banquemisr.enums.NotificationType;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotifications();
    Notification createNotification(Long taskId, Long userId, NotificationType notificationType);



}
