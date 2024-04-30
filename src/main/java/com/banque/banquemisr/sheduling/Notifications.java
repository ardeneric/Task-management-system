package com.banque.banquemisr.sheduling;

import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.enums.NotificationType;
import com.banque.banquemisr.event.NotificationEvent;
import com.banque.banquemisr.service.TaskService;
import com.banque.banquemisr.service.impl.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Notifications {

    private final TaskService taskService;
    private final EmailService emailService;
    private final ApplicationEventPublisher eventPublisher;


    @Scheduled(cron = "0 0 9 * * *") // Run every day at 9:00 AM
    public void sendTaskDeadlineNotifications() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Task> tasks = taskService.getTasksDueToday(tomorrow);
        for (Task task : tasks) {
            eventPublisher.publishEvent(new NotificationEvent(
                    this,
                    task.getId(),
                    task.getUser().getId(),
                    NotificationType.UPCOMING_DEADLINE,
                    "Task Deadline Reminder. Task deadline is Tomorrow: ".concat(task.getTitle())));
        }
    }
}
