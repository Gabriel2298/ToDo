package com.app.ToDo.controller;

import com.app.ToDo.models.Task;
import com.app.ToDo.service.impl.TaskServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling tasks.
 */
@RestController
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    /**
     * Retrieves all tasks and adds them to the Model for rendering on the view.
     *
     * @param model the Model object to which the tasks are added
     * @return the view name for displaying the tasks
     */
    @GetMapping
    public String getTasks(@NotNull Model model) {
        List<Task> tasks = taskServiceImpl.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    /**
     * Creates a new task with the given title, description, and completion status.
     *
     * @param title the title of the task
     * @param description the description of the task
     * @param completed true if the task is already completed, false otherwise
     * @return a redirection to the home page "/"
     */
    @PostMapping("/tasks")
    public String createTask(String title, String description, boolean completed) {
        taskServiceImpl.createTask(title,description, completed);
        return "redirect:/";
    }

    /**
     * Deletes a task with the specified ID.
     *
     * @param id the ID of the task to be deleted
     * @return a redirection to the home page "/"
     */
    @GetMapping("{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskServiceImpl.deleteTask(id);
        return "redirect:/";
    }

    /**
     * Toggles the completion status of a task with the specified ID.
     *
     * @param id the ID of the task to toggle
     * @return a redirection to the home page "/"
     */
    @GetMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable Long id) {
        taskServiceImpl.toggleTask(id);
        return "redirect:/";
    }
}
