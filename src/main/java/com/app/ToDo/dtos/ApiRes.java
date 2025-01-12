package com.app.ToDo.dtos;


public record ApiRes(String message, boolean success) {
    public String setMessage(String message) {
        return this.message;
    }

    public boolean setSuccess(boolean success) {
        return this.success;
    }

    @Override
    public String toString() {
        return "ApiRes{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
