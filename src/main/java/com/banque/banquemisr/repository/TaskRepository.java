package com.banque.banquemisr.repository;

import com.banque.banquemisr.entity.Task;
import com.banque.banquemisr.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContainingAndDescriptionContainingAndStatusAndDueDate(String title, String description, TaskStatus status, LocalDate dueDate);

    List<Task> findByDueDate(LocalDate date);
}
