package com.banque.banquemisr.model.dto;

import com.banque.banquemisr.enums.TaskPriority;
import com.banque.banquemisr.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

/**
 * @author ericarden
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    @NotBlank(message = "title is mandatory")
    private String title;
    private String description;
    @NotNull(message = "Status is mandatory")
    private TaskStatus status;
    @NotNull(message = "Priority is mandatory")
    private TaskPriority priority;
    @NotNull(message = "Due date is mandatory")
    private LocalDate dueDate;
}
