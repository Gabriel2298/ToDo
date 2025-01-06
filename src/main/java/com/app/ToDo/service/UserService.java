package com.app.ToDo.service;

import com.app.ToDo.dtos.UserDto;

import java.util.List;

public interface UserService{

    // create users
    UserDto createUser(UserDto userDto);
    // update users
    UserDto updateUser(UserDto userDto, Long userId);
    // delete users
    void deletedUser(Long userId);
    // get by email users
    UserDto getUser(String email);

    //get
    UserDto getUser(Long userId);
    //get all users
    List<UserDto> getAllUsers();
    // user login
    UserDto userLogin(String email, String password);
}
