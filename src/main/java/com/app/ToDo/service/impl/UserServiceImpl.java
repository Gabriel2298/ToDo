package com.app.ToDo.service.impl;

import com.app.ToDo.dtos.CreateUserDto;
import com.app.ToDo.models.User;
import com.app.ToDo.repositories.UserRepository;
import com.app.ToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
private UserRepository userRepository;

    @Override
    public CreateUserDto createUser(CreateUserDto createUserDto) {
        User user = this.userRepository.save(this.DtoToUser(createUserDto));
        return null;
    }

    @Override
    public CreateUserDto updateUser(CreateUserDto createUserDto, Long userId) {
        return null;
    }

    public User DtoToUser(CreateUserDto createUserDto){
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        return user;
    }
}
