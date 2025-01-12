package com.app.ToDo.service.impl;

import com.app.ToDo.dtos.TasksDto;
import com.app.ToDo.models.Task;
import com.app.ToDo.models.User;
import com.app.ToDo.repositories.TaskRepository;
import com.app.ToDo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void testCreateTask_Successful() {
        // Arrange
        TaskServiceImpl taskService = new TaskServiceImpl(taskRepository, userRepository);
        String email = "test@example.com";

        TasksDto tasksDto = new TasksDto();
        tasksDto.setTitle("Test Task");
        tasksDto.setDescription("This is a test task");

        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Test Task");
        savedTask.setDescription("This is a test task");
        savedTask.setUser(user);

        when(userRepository.findByEmail(email)).thenReturn(user);
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        // Act
        TasksDto result = taskService.createTask(tasksDto, email);

        // Assert
        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
        assertEquals("This is a test task", result.getDescription());
        verify(userRepository, times(1)).findByEmail(email);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testCreateTask_UserNotFound() {
        // Arrange
        TaskServiceImpl taskService = new TaskServiceImpl(taskRepository, userRepository);
        String email = "nonexistent@example.com";

        TasksDto tasksDto = new TasksDto();
        tasksDto.setTitle("Test Task");
        tasksDto.setDescription("This is a test task");

        when(userRepository.findByEmail(email)).thenReturn(null);

        // Act & Assert
        ResourceAccessException exception = assertThrows(ResourceAccessException.class, () -> {
            taskService.createTask(tasksDto, email);
        });

        assertEquals("User not found with email: " + email, exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void testCreateTask_InvalidEmail() {
        // Arrange
        TaskServiceImpl taskService = new TaskServiceImpl(taskRepository, userRepository);
        String email = "";

        TasksDto tasksDto = new TasksDto();
        tasksDto.setTitle("Test Task");
        tasksDto.setDescription("This is a test task");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask(tasksDto, email);
        });

        assertEquals("Email must not be null or empty!", exception.getMessage());
        verify(userRepository, never()).findByEmail(anyString());
        verify(taskRepository, never()).save(any(Task.class));
    }
}