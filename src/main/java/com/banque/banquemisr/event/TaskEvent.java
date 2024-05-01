package com.banque.banquemisr.event;

import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.enums.OperationType;
import org.springframework.context.ApplicationEvent;

public class TaskEvent extends ApplicationEvent {
    private final transient Task task;
    private final OperationType operationType;

    public TaskEvent(Object source, Task task, OperationType operationType) {
        super(source);
        this.task = task;
        this.operationType = operationType;
    }

    public Task getTask() {
        return task;
    }

    public OperationType getOperationType() {
        return operationType;
    }
}
