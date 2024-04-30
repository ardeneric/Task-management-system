package com.banque.banquemisr.model.dto;

import com.banque.banquemisr.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationDto {
    private Long taskId;
    private Long userId;
    private NotificationType notificationType;
    private String message;

}
