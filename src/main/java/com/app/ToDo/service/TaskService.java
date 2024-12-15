package com.app.ToDo.service;

import com.app.ToDo.models.Task;
import com.app.ToDo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

//    This method show all tasks!
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

//    This method add new tasks!
    public void createTask(String title, String description ) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setCompleted(false);
        taskRepository.save(task);
    }

//    This method deleted tasks by id;
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

//    This method can make tasks completed or not completed.
    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }

}
