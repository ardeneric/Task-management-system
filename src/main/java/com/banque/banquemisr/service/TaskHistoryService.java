package com.banque.banquemisr.service;

import com.banque.banquemisr.entity.TaskHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskHistoryService {

    TaskHistory createTaskHistory(TaskHistory task);

    Page<TaskHistory> getAllTaskHistory(Pageable pageable);

    List<TaskHistory> getTaskHistoryByUser(String username);

}
