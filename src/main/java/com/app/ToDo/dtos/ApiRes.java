package com.app.ToDo.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiRes {
    private String message;
    private Boolean success;

    public ApiRes(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
