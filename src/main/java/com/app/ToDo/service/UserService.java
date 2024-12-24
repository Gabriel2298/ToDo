package com.app.ToDo.service;

import com.app.ToDo.dtos.CreateUserDto;

public interface UserService{

    // create users
    CreateUserDto createUser(CreateUserDto createUserDto);

    // update users
    CreateUserDto updateUser( CreateUserDto createUserDto, Long userId);
}
