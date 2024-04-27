package banque.banquemisr.controller;

import banque.banquemisr.entity.Notification;
import banque.banquemisr.enums.NotificationType;
import banque.banquemisr.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok().body(notifications);
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestParam Long taskId, @RequestParam Long userId, @RequestParam NotificationType notificationType) {
        Notification notification = notificationService.createNotification(taskId, userId, notificationType);
        if (notification == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }
}
