package com.app.ToDo.service;

import com.app.ToDo.dtos.TasksDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    //create
    TasksDto createTask(TasksDto tasksDto, String userId);
    //update
    TasksDto updateTask(TasksDto tasksDto, Long taskId);
    //delete
    void deleteTask(Long taskId);
    //get
    TasksDto getTask(Long taskId);
    //getAll
    List<TasksDto> getAllTasks();
    //getByUser
    List<TasksDto> getTasksByUser(String userId);
}
