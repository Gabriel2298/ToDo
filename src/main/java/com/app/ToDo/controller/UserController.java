package com.app.ToDo.controller;

import com.app.ToDo.dtos.LoginBody;
import com.app.ToDo.dtos.UserDto;
import com.app.ToDo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController is a REST controller that handles CRUD operations for users,
 * user authentication, and retrieval of user data.
 * It defines endpoints for creating, updating, deleting, retrieving
 * user details, authenticating users, and fetching all users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final String GET_ALL_USERS_ENDPOINT = "/getAllUsers";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
    * Creates a new user using the provided UserDto object.
    *
    * @param user the UserDto object containing the details of the user to be created
    * @return a ResponseEntity containing the created UserDto object
    */
   //Create
   @PostMapping("/createUser")
    public ResponseEntity<UserDto>  createUser(@RequestBody UserDto user){
       UserDto userDto = this.userService.createUser(user);
       userDto.setName(user.getName());
       userDto.setEmail(user.getEmail());
       userDto.setPassword(user.getPassword());
       userDto.setUserName(user.getUserName());
       userDto.setAge(user.getAge());
       userDto.setRegistrationDate(user.getRegistrationDate());
       return ResponseEntity.ok(userDto);
   }

   /**
    * Updates an existing user's details based on the provided UserDto object and userId.
    *
    * @param user the UserDto object containing updated information about the user
    * @param userId the ID of the user to be updated
    * @return a ResponseEntity containing the updated UserDto object
    */
   //Update
   @PostMapping("/update")
   public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, Long userId){
       UserDto userDto = this.userService.updateUser(user, userId);
       return ResponseEntity.ok(userDto);
   }

   /**
    * Deletes a user based on the provided user ID.
    *
    * @param userId the ID of the user to be deleted
    * @return a ResponseEntity containing a confirmation message indicating successful deletion
    */
   //Deleted
   @DeleteMapping("/{userId}")
   public ResponseEntity<String> deletedUser(@PathVariable Long userId){
       this.userService.deletedUser(userId);
       return ResponseEntity.ok("User deleted successfully!");
   }

   /**
    * Retrieves user details based on the provided user ID.
    *
    * @param email the ID of the user to be retrieved
    * @return a ResponseEntity containing the UserDto object with the user's details
    */
   //get user by email
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail( @PathVariable String email){
        UserDto userDto = this.userService.getUser(email);
        userDto.setName(userDto.getName());
        userDto.setEmail(userDto.getEmail());
        userDto.setRegistrationDate(userDto.getRegistrationDate());
        userDto.setPassword(userDto.getPassword());
        userDto.setUserName(userDto.getUserName());
        userDto.setAge(userDto.getAge());
        userDto.setRegistrationDate(userDto.getRegistrationDate());
        return ResponseEntity.ok(userDto);
    }

    /**
     * Retrieves all users.
     *
     * @return a ResponseEntity containing an iterable collection of UserDto objects representing all users
     */
    //get all
    @GetMapping(GET_ALL_USERS_ENDPOINT)
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = this.userService.getAllUsers();
//    var users = this.userService.getAllUsers();
        System.out.println("Users: " + users.size());
        return ResponseEntity.ok(users);
    }

    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param loginBody the LoginBody object containing the email and password for the user
     * @return a ResponseEntity containing the authenticated UserDto object if successful,
     *         or an unauthorized status if authentication fails
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> userLogin(@RequestBody LoginBody loginBody){
        UserDto apiRes = new UserDto();
        try {
            apiRes = this.userService.userLogin(loginBody.getEmail(), loginBody.getPassword());
            return ResponseEntity.ok(apiRes);
        } catch (Exception e) {
            return new ResponseEntity<>(apiRes, HttpStatus.UNAUTHORIZED);
        }
    }
}
