package com.banque.banquemisr.service.impl;

import com.banque.banquemisr.entity.Notification;
import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.entity.User;
import com.banque.banquemisr.enums.NotificationType;
import com.banque.banquemisr.repository.NotificationRepository;
import com.banque.banquemisr.repository.TaskRepository;
import com.banque.banquemisr.repository.UserRepository;
import com.banque.banquemisr.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification createNotification(Long taskId, Long userId, NotificationType notificationType, String message) {
        log.info("Creating notification :: {} ", notificationType);
        Task task = taskRepository.findById(taskId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (ObjectUtils.isEmpty(task) || ObjectUtils.isEmpty(user)) {
            log.error("Cannot create notification for {} since task or user does not exist!", userId);
            return null;
        }

        Notification notification = new Notification();
        notification.setTask(task);
        notification.setUser(user);
        notification.setNotificationType(notificationType);
        notification.setMessage(message);

        emailService.sendEmail(user.getEmail(), NotificationType.IMPORTANT_UPDATE.name(), message);
        return notificationRepository.save(notification);
    }
}
