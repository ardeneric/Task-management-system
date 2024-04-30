package com.banque.banquemisr.service.impl;

import com.banque.banquemisr.entity.TaskHistory;
import com.banque.banquemisr.repository.TaskHistoryRepository;
import com.banque.banquemisr.service.TaskHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskHistoryServiceImpl implements TaskHistoryService {
    private final TaskHistoryRepository taskHistoryRepository;
    @Override
    public TaskHistory createTaskHistory(TaskHistory taskHistory) {
        return taskHistoryRepository.save(taskHistory);
    }

    @Override
    public Page<TaskHistory> getAllTaskHistory(Pageable pageable) {
        return taskHistoryRepository.findAll(pageable);
    }

    @Override
    public List<TaskHistory> getTaskHistoryByUser(String username) {

        return taskHistoryRepository.findByTask_User_Username(username);
    }
}
