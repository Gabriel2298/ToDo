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

    /**
     * Creates a new task for a specific user.
     *
     * @param tasks the TasksDto object containing the details of the task to be created
     * @param userId the ID of the user for whom the task is created
     * @return a ResponseEntity containing the created TasksDto object and HTTP status code
     */
    @PostMapping("/tasks")
    public ResponseEntity<TasksDto> createTasks(@RequestBody TasksDto tasks, @PathVariable String userId) {
        TasksDto task = this.taskService.createTask(tasks, userId);
        return new ResponseEntity<TasksDto>(tasks, HttpStatus.CREATED);
    }

    /**
     * Updates an existing task based on the provided task details and the task ID.
     *
     * @param tasks the TasksDto object containing the updated details of the task
     * @param tasksId the ID of the task to be updated
     * @return a ResponseEntity containing the updated TasksDto object and HTTP status code
     */
    @PutMapping("/{tasksId}")
    public ResponseEntity<TasksDto> updateTasks(@RequestBody TasksDto tasks, @PathVariable Long tasksId) {
        TasksDto updateTask = this.taskService.updateTask(tasks, tasksId);
        return new ResponseEntity<TasksDto>(updateTask, HttpStatus.OK);
    }


    /**
     * Deletes a task based on the provided task ID.
     *
     * @param taskId the ID of the task to be deleted
     * @return a ResponseEntity containing an ApiRes object with a success message and HTTP status code
     */
    @GetMapping("{id}/delete")
    public ResponseEntity<ApiRes> deleteTask(@PathVariable Long taskId) {
        this.taskService.deleteTask(taskId);
        return new ResponseEntity<ApiRes>(new ApiRes("Task deleted", true), HttpStatus.OK);
    }

    /**
     * Retrieves the list of tasks associated with a specific user.
     *
     * @param userId the ID of the user whose tasks are being retrieved
     * @return a ResponseEntity containing a list of TasksDto objects for the specified user and an HTTP status code
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<TasksDto>> getTasksByUser(@PathVariable String userId) {
        List<TasksDto> Task = this.taskService.getTasksByUser(userId);
        return new ResponseEntity<List<TasksDto>>(Task, HttpStatus.OK);
    }

    /**
     * Retrieves the list of all tasks.
     *
     * @return a ResponseEntity containing a list of TasksDto objects and an HTTP status code
     */
    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<List<TasksDto>> getTasks(){
        List<TasksDto> Task = this.taskService.getAllTasks();
        return new ResponseEntity<List<TasksDto>>(Task, HttpStatus.OK);
    }
}


