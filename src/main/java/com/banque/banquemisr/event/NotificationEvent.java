package com.banque.banquemisr.event;

import com.banque.banquemisr.enums.NotificationType;
import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {
    private final Long taskId;
    private final Long userId;
    private final NotificationType notificationType;
    private final String message;

    public NotificationEvent(Object source, Long taskId, Long userId, NotificationType notificationType, String message) {
        super(source);
        this.taskId = taskId;
        this.userId = userId;
        this.message = message;
        this.notificationType = notificationType;
    }

    public Long getTaskId() {
        return taskId;
    }
    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

}
