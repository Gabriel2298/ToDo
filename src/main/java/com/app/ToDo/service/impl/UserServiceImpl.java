package com.app.ToDo.service.impl;

import com.app.ToDo.dtos.UserDto;
import com.app.ToDo.models.User;
import com.app.ToDo.repositories.UserRepository;
import com.app.ToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
private UserRepository userRepository;

    // create users
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userRepository.save(this.DtoToUser(userDto));
        return this.UserToDto(user);
    }

    // update users
    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        return this.UserToDto(user);
    }

    @Override
    public void deletedUser(Long userId){
        User user = this.userRepository.findById(userId).orElseThrow();
        this.userRepository.delete(user);
    }

    @Override
    public UserDto getUser(Long userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDto userLogin(String email, String password) {
        return null;
    }

    public User DtoToUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto UserToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(userDto.getName());
        userDto.setEmail(userDto.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
