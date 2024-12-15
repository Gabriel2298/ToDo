package com.app.ToDo.repositories;

import com.app.ToDo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TaskRepository extends JpaRepository<Task, Long> {
}
