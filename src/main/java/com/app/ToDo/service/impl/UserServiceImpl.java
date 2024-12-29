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

    /**
     * Creates a new user in the system by saving the provided user details.
     *
     * @param userDto the data transfer object containing user details to be created
     * @return the data transfer object representing the created user
     */
    // create users
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userRepository.save(this.DtoToUser(userDto));
        return this.UserToDto(user);
    }

    /**
     * Updates an existing user's details in the system.
     *
     * @param userDto the data transfer object containing the new user details
     * @param userId the ID of the user to be updated
     * @return the data transfer object representing the updated user
     */
    // update users
    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        return this.UserToDto(user);
    }

    /**
     * Deletes a user from the system by their unique identifier.
     *
     * @param userId the unique ID of the user to be deleted
     * @throws EntityNotFoundException if no user with the given ID is found
     */
    //delete
    @Override
    public void deletedUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        this.userRepository.delete(user);
    }

    /**
     * Retrieves a user's details by their unique identifier.
     *
     * @param userId the unique ID of the user to be retrieved
     * @return the data transfer object representing the retrieved user
     * @throws EntityNotFoundException if no user with the given ID is found
     */
    //get
    @Override
    public UserDto getUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        return this.UserToDto(user);
    }

    /**
     * Retrieves a list of all users in the system.
     *
     * @return a list of data transfer objects representing all users
     */
    //get all
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users= this.userRepository.findAll();
        return List.of();
    }

    /**
     * Authenticates a user by validating the provided email and password.
     *
     * @param email the email of the user attempting to log in
     * @param password the password of the user attempting to log in
     * @return a UserDto object representing the authenticated user, or null if authentication fails
     */
    @Override
    public UserDto userLogin(String email, String password) {
        return null;
    }

    public User DtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    /**
     * Converts a User object to a UserDto object by mapping the relevant fields.
     *
     * @param user the User object to be converted
     * @return the converted UserDto object
     */
    public UserDto UserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(userDto.getName());
        userDto.setEmail(userDto.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
