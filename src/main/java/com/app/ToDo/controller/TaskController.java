package com.app.ToDo.controller;

import com.app.ToDo.dtos.ApiRes;
import com.app.ToDo.dtos.TasksDto;
import com.app.ToDo.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TaskController is a REST controller that provides endpoints for
 * managing tasks. It supports operations such as creating, updating,
 * deleting tasks, as well as retrieving tasks based on users or
 * retrieving all tasks.
 */

@Controller
@RequestMapping("/api")
@CrossOrigin
public class TaskController {

    private static final String CREATE_TASK_ENDPOINT = "/createTasks";
    private static final String UPDATE_TASK_ENDPOINT = "/updateTasks";
    private static final String DELETE_TASK_ENDPOINT = "/tasks/delete/{tasksId}-{email}";
    private static final String GET_TASKS_BY_USER_EMAIL_ENDPOINT = "/tasks/{email}";
    private static final String GET_ALL_TASKS_ENDPOINT = "/getAllTasks";
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    /**
     * Creates a new task based on the provided task details.
     *
     * @param tasks the TasksDto object containing the details of the task to be created
     * @return a ResponseEntity containing the created TasksDto object and an HTTP status code
     * @throws IllegalArgumentException if the provided email in the task details is null or empty
     */
    @PostMapping(CREATE_TASK_ENDPOINT)
    public ResponseEntity<TasksDto> createTasks(@RequestBody TasksDto tasks) {
        TasksDto createTasks = this.taskService.createTask(tasks, tasks.getEmail());
        if (tasks.getEmail() == null || tasks.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email must not be null or empty!");
        }

        return new ResponseEntity<>(createTasks, HttpStatus.CREATED);
    }
    /**
     * Updates an existing task based on the provided task details and task ID.
     *
     * @param tasks the TasksDto object containing updated details of the task
     * @param tasksId the ID of the task to be updated
     * @return a ResponseEntity containing the updated TasksDto object and an HTTP status code
     */
    @PutMapping(UPDATE_TASK_ENDPOINT)
    public ResponseEntity<TasksDto> updateTasks(@RequestBody TasksDto tasks,Long tasksId) {
        TasksDto updateTask = this.taskService.updateTask(tasks, tasksId);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }


    /**
     * Deletes a task based on the provided task ID and user email.
     *
     * @param tasksId the ID of the task to be deleted
     * @param email the email of the user requesting the deletion
     * @return a ResponseEntity containing an ApiRes object with a message and status, and an HTTP status code
     */
    @DeleteMapping(value = DELETE_TASK_ENDPOINT, produces = "application/json")
    public ResponseEntity<ApiRes> deleteTask( @PathVariable Long tasksId,@PathVariable String email) {
        this.taskService.deleteTask(tasksId,email);
        return new ResponseEntity<>(new ApiRes("Task deleted", true), HttpStatus.OK);
    }

    /**
     * Retrieves the list of tasks associated with a specific user, identified by their email address.
     *
     * @param email the email of the user whose tasks are to be retrieved
     * @return a ResponseEntity containing a list of TasksDto objects associated with the user and an HTTP status code
     */
    @GetMapping(GET_TASKS_BY_USER_EMAIL_ENDPOINT)
    public ResponseEntity<List<TasksDto>> getTasksByUser(@PathVariable String email) {
        List<TasksDto> Task = this.taskService.getTasksByUserEmail(email);
        return new ResponseEntity<>(Task, HttpStatus.OK);
    }

    /**
     * Retrieves the list of all tasks in the system.
     *
     * @return a ResponseEntity containing a list of TasksDto objects representing all tasks and an HTTP status code
     */
    @GetMapping(GET_ALL_TASKS_ENDPOINT)
    @CrossOrigin
    public ResponseEntity<List<TasksDto>> getTasks(){
        List<TasksDto> Task = this.taskService.getAllTasks();
        return new ResponseEntity<>(Task, HttpStatus.OK);
    }
}


