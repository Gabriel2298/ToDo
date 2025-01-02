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

    /**
     * Creates a new task and associates it with the user identified by the provided user ID.
     * Persists the task to the database and returns the created task as a TasksDto.
     *
     * @param tasksDto the task details to be created, including title, description, and date
     * @param userId the identifier (email) of the user who owns the task
     * @return the created task details as a TasksDto object
     */
    //    This method add new tasks!
    @Override
    public TasksDto createTask(TasksDto tasksDto, String userId) {
        User user = userRepository.findByEmail(userId);
        if (user == null) {
            throw new ResourceAccessException("User not found with email: " + userId);
        }
        tasksDto.setDate(new Date());
        Task task = DtoToTask(tasksDto);
        task.setUser(user);
        Task result = taskRepository.save(task);
        return this.TasksToDto(result);
    }

    /**
     * Updates an existing task with the provided task details.
     *
     * @param tasksDto the new task details including title, description, and date
     * @param taskId the identifier of the task to be updated
     * @return the updated task details as a TasksDto object
     */
    // This method can update the task!
    @Override
    public TasksDto updateTask(TasksDto tasksDto, Long taskId) {
        Task task = this.taskRepository.findById(taskId).orElseThrow(()->new ResourceAccessException("not found"));
        tasksDto.setDate(new Date());
        task.setTitle(tasksDto.getTitle());
        task.setDate(tasksDto.getDate());
        task.setDescription(tasksDto.getDescription());
        Task result = this.taskRepository.save(task);
        return this.TasksToDto(result);
    }

    /**
     * Deletes an existing task by its unique identifier.
     * The task is retrieved from the repository and removed.
     * If the task does not exist, a ResourceAccessException is thrown.
     *
     * @param taskId the unique identifier of the task to be deleted
     */
    // This method can delete the task!
    @Override
    public void deleteTask(Long taskId) {
        Task task = this.taskRepository.findById(taskId).orElseThrow(()->new ResourceAccessException("not found"));
        this.taskRepository.delete(task);
    }

    /**
     * Retrieves a task by its unique identifier and converts it into a TasksDto object.
     *
     * @param taskId the unique identifier of the task to be retrieved
     * @return the data transfer object representation of the task
     * @throws ResourceAccessException if the task with the given id is not found
     */
    // This method get
    @Override
    public TasksDto getTask(Long taskId){
        Task task = this.taskRepository.findById(taskId).orElseThrow(()->new ResourceAccessException("not found"));
        return this.TasksToDto(task);
    }

    /**
     * Retrieves a list of tasks associated with a specific user.
     *
     * @param userId the email or identifier of the user whose tasks are to be retrieved
     * @return a list of task data transfer objects (TasksDto) associated with the specified user
     */
    // This method get task by  user!
    @Override
    public List<TasksDto> getTasksByUser(String userId){
       User user = userRepository.findByEmail(userId);
       List<Task> tasks = this.taskRepository.findAllByUser(user);
       List<TasksDto> allTasks = tasks.stream().map(this::TasksToDto).collect(Collectors.toList());
       return allTasks;


    }

    /**
     * Retrieves all tasks from the repository and converts them to DTOs.
     *
     * @return a list of TasksDto objects representing all tasks.
     */
    // This method show all tasks!
    @Override
    public List<TasksDto> getAllTasks(){
        List<Task> tasks = this.taskRepository.findAll();
        List<TasksDto> allTasks = tasks.stream().map((task)->this.TasksToDto(task)).collect(Collectors.toList());
        return allTasks;
    }

    /**
     * Converts a Task object into a TasksDto object.
     *
     * @param task the Task object to be converted
     * @return the resulting TasksDto object containing the mapped values from the Task
     */
    public TasksDto TasksToDto(Task task){
        TasksDto tasksDto = new TasksDto();
        tasksDto.setId(task.getId());
        tasksDto.setTitle(task.getTitle());
        tasksDto.setDate(task.getDate());
        tasksDto.setDescription(task.getDescription());
        tasksDto.setUserDto(this.UserToDto(task.getUser()));
        return tasksDto;
    }
    /**
     * Converts a given TasksDto object to a Task object by mapping its fields.
     *
     * @param tasksDto the data transfer object containing task details to be converted
     * @return the Task object with data populated from the provided TasksDto
     */
    public Task DtoToTask(TasksDto tasksDto){
        Task task = new Task();
        task.setTitle(tasksDto.getTitle());
        task.setDate(tasksDto.getDate());
        task.setDescription(tasksDto.getDescription());
        return task;
    }

    /**
     * Converts a User object to a UserDto object.
     *
     * @param user the User object to be converted
     * @return the converted UserDto object
     */
    public UserDto UserToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    /**
     * Converts a UserDto object to a User object.
     *
     * @param userDto the source object containing user data to be converted
     * @return a User object populated with data from the given UserDto
     */
    public User DtoToUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
