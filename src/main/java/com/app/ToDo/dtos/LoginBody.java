package com.app.ToDo.dtos;

import lombok.ToString;


public class LoginBody {
    public  String email;

    @ToString.Exclude
    public  String password;

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
}
