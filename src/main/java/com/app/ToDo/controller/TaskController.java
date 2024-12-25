package com.app.ToDo.controller;

import com.app.ToDo.models.Task;
import com.app.ToDo.service.impl.TaskServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @GetMapping
    public String getTasks(@NotNull Model model) {
        List<Task> tasks = taskServiceImpl.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(String title, String description, boolean completed) {
        taskServiceImpl.createTask(title,description, completed);
        return "redirect:/";
    }

    @GetMapping("{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskServiceImpl.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable Long id) {
        taskServiceImpl.toggleTask(id);
        return "redirect:/";
    }
}
