package banque.banquemisr.service;

import banque.banquemisr.entity.Notification;
import banque.banquemisr.enums.NotificationType;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotifications();
    Notification createNotification(Long taskId, Long userId, NotificationType notificationType);



}
