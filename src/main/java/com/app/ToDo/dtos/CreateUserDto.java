package com.app.ToDo.dtos;

import com.app.ToDo.models.Task;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@ToString
public class CreateUserDto implements Serializable {
private String name;
private Integer age;
private LocalDate registrationDate;
private String email;
private String userName;
private boolean active;
private List<Task> tasks;

}
