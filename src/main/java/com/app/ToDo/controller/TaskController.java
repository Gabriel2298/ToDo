package com.app.ToDo.controller;

import com.app.ToDo.dtos.ApiRes;
import com.app.ToDo.dtos.TasksDto;
import com.app.ToDo.service.TaskService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling tasks.
 */

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<TasksDto> createTasks(@RequestBody TasksDto tasks, @PathVariable String userId) {
        TasksDto task = this.taskService.createTask(tasks, userId);
        return new ResponseEntity<TasksDto>(tasks, HttpStatus.CREATED);
    }

    @PutMapping("/{tasksId}")
    public ResponseEntity<TasksDto> updateTasks(@RequestBody TasksDto tasks, @PathVariable Long tasksId) {
        TasksDto updateTask = this.taskService.updateTask(tasks, tasksId);
        return new ResponseEntity<TasksDto>(updateTask, HttpStatus.OK);
    }


    @GetMapping("{id}/delete")
    public ResponseEntity<ApiRes> deleteTask(@PathVariable Long taskId) {
        this.taskService.deleteTask(taskId);
        return new ResponseEntity<ApiRes>(new ApiRes("Task deleted", true), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TasksDto>> getTasksByUser(@PathVariable String userId) {
        List<TasksDto> Task = this.taskService.getTasksByUser(userId);
        return new ResponseEntity<List<TasksDto>>(Task, HttpStatus.OK);
    }

    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<List<TasksDto>> getTasks(){
        List<TasksDto> Task = this.taskService.getAllTasks();
        return new ResponseEntity<List<TasksDto>>(Task, HttpStatus.OK);
    }
}


