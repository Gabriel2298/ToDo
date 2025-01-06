package com.app.ToDo.service.impl;

import com.app.ToDo.dtos.UserDto;
import com.app.ToDo.models.User;
import com.app.ToDo.repositories.UserRepository;
import com.app.ToDo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    @Override
    public UserDto getUser(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User with email:" + email +"not found");
        }
        return this.UserToDto(user);
    }

    // get
    @Override
    public UserDto getUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User with id:" + userId +"not found"));
        return this.UserToDto(user) ;
    }
    /**
     * Retrieves a list of all users in the system.
     *
     * @return a list of UserDto objects representing all users
     */
    //get all
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("No users found in the system.");
        }
        return users.stream().map(this::UserToDto).toList();
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

    /**
     * Converts a UserDto object to a User object by mapping the relevant fields.
     *
     * @param userDto the data transfer object containing user details to be converted
     * @return the User object created from the given UserDto
     */
    public User DtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUserName());
        user.setActive(true);
        user.setAge(userDto.getAge());
        user.setRegistrationDate(userDto.getRegistrationDate());
        return user;
    }

    /**
     * Converts a User object to a UserDto object by mapping the relevant fields.
     *
     * @param user the User object to be converted
     * @return the converted UserDto object
     */
    public UserDto UserToDto(User user) {
        if (user == null) {
            throw new EntityNotFoundException("Cannot convert null user to UserDto.");
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUserName(user.getUserName());
        userDto.setActive(true);
        userDto.setAge(user.getAge());
        userDto.setRegistrationDate(user.getRegistrationDate());
        return userDto;
    }
}
