package com.app.ToDo.dtos;


public class ApiRes {
    private final String message;
    private final boolean success;

    public ApiRes(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    @Override
    public String toString() {
        return "ApiRes{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
