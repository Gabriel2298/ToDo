package com.app.ToDo.exception;


public class UserServicesException extends RuntimeException {
    private String errorCode;

    public UserServicesException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
