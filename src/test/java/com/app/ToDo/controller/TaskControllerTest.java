package com.app.ToDo.controller;

import com.app.ToDo.dtos.TasksDto;
import com.app.ToDo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void createTasks_ValidRequest_ReturnsCreatedTask() throws Exception {
        TasksDto tasksDto = new TasksDto();
        tasksDto.setId(1L);
        tasksDto.setEmail("gabimihaila98@example.com");
        tasksDto.setTitle("Task1");
        tasksDto.setDescription("Description");

        Mockito.when(taskService.createTask(any(TasksDto.class), eq("test@example.com"))).thenReturn(tasksDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/createTasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                " \"email\": \"test@example.com\",\n" +
                                "\"title\": \"New Task\",\n" +
                                "\"description\": \"Task Description\"\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.description").value("Task Description"));
    }

    @Test
    public void createTasks_InvalidRequest_ReturnsBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/createTasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"email\": \"\",\n" +
                                "  \"title\": \"New Task\",\n" +
                                "  \"description\": \"Task Description\"\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}