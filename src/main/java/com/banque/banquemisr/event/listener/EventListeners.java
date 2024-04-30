package com.banque.banquemisr.event.listener;

import com.banque.banquemisr.entity.TaskHistory;
import com.banque.banquemisr.event.NotificationEvent;
import com.banque.banquemisr.event.TaskEvent;
import com.banque.banquemisr.service.NotificationService;
import com.banque.banquemisr.service.TaskHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventListeners {

    private final TaskHistoryService taskHistoryService;
    private final NotificationService notificationService;

    @Async
    @EventListener
    public void handleTaskEvent(TaskEvent event) {
        log.info("Creating task history :: {} ", event);
        TaskHistory history = new TaskHistory();
        history.setChanges(event.getOperationType().name());
        history.setTask(event.getTask());
        taskHistoryService.createTaskHistory(history);
    }

    @Async
    @EventListener
    public void handleNotificationEvent(NotificationEvent event) {
        log.info("Creating notification :: {} ", event);
        notificationService.createNotification(
                event.getTaskId(),
                event.getUserId(),
                event.getNotificationType(),
                event.getMessage());
    }
}
