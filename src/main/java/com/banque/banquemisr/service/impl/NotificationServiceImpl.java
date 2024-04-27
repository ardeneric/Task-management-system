package banque.banquemisr.service.impl;

import banque.banquemisr.entity.Notification;
import banque.banquemisr.entity.Task;
import banque.banquemisr.entity.User;
import banque.banquemisr.enums.NotificationType;
import banque.banquemisr.repository.NotificationRepository;
import banque.banquemisr.repository.TaskRepository;
import banque.banquemisr.repository.UserRepository;
import banque.banquemisr.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification createNotification(Long taskId, Long userId, NotificationType notificationType) {
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
        notification.setSentAt(new Date());

        return notificationRepository.save(notification);
    }
}
