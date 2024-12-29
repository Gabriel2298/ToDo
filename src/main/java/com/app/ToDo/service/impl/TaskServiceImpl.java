package com.app.ToDo.service.impl;

import com.app.ToDo.dtos.TasksDto;
import com.app.ToDo.dtos.UserDto;
import com.app.ToDo.models.Task;
import com.app.ToDo.models.User;
import com.app.ToDo.repositories.TaskRepository;
import com.app.ToDo.repositories.UserRepository;
import com.app.ToDo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.ResourceAccessException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    //    This method add new tasks!
    @GetMapping
    public TasksDto createTask(TasksDto tasksDto, String userId) {
        User user = userRepository.findByEmail(userId);
        tasksDto.setDate(new Date());
        Task task = DtoToTask(tasksDto);
        task.setUser(user);
        Task result = taskRepository.save(task);
        return this.TasksToDto(result);
    }

    // This method can update the task!
    @GetMapping
    public TasksDto updateTask(TasksDto tasksDto, Long taskId) {
        Task task = this.taskRepository.findById(taskId).orElseThrow(()->new ResourceAccessException("not found"));
        tasksDto.setDate(new Date());
        task.setTitle(tasksDto.getTitle());
        task.setDate(tasksDto.getDate());
        task.setDescription(tasksDto.getDescription());
        Task result = this.taskRepository.save(task);
        return this.TasksToDto(result);
    }

    // This method can delete the task!
    @DeleteMapping
    public void deleteTask(Long taskId) {
        Task task = this.taskRepository.findById(taskId).orElseThrow(()->new ResourceAccessException("not found"));
        this.taskRepository.delete(task);
    }

    // This method get
    @Override
    public TasksDto getTask(Long taskId){
        Task task = this.taskRepository.findById(taskId).orElseThrow(()->new ResourceAccessException("not found"));
        return this.TasksToDto(task);
    }

    // This method get task by  user!
    @Override
    public List<TasksDto> getTasksByUser(String userId){
       User user = userRepository.findByEmail(userId);
       List<Task> tasks = this.taskRepository.findAllByUser(user);
       List<TasksDto> allTasks = tasks.stream().map(this::TasksToDto).collect(Collectors.toList());
       return allTasks;


    }

    // This method show all tasks!
    @Override
    public List<TasksDto> getAllTasks(){
        List<Task> tasks = this.taskRepository.findAll();
        List<TasksDto> allTasks = tasks.stream().map((task)->this.TasksToDto(task)).collect(Collectors.toList());
        return allTasks;
    }

    public TasksDto TasksToDto(Task task){
        TasksDto tasksDto = new TasksDto();
        tasksDto.setId(task.getId());
        tasksDto.setTitle(task.getTitle());
        tasksDto.setDate(task.getDate());
        tasksDto.setDescription(task.getDescription());
        tasksDto.setUserDto(this.UserToDto(task.getUser()));
        return tasksDto;
    }
    public Task DtoToTask(TasksDto tasksDto){
        Task task = new Task();
        task.setTitle(tasksDto.getTitle());
        task.setDate(tasksDto.getDate());
        task.setDescription(tasksDto.getDescription());
        return task;
    }

    public UserDto UserToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public User DtoToUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
