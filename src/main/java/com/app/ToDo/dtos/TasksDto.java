package com.app.ToDo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class TasksDto {
    private Long id;
    private String Title;
    private String Description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private UserDto userDto;
}
