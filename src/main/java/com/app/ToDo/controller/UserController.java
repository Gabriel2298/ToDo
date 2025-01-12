package com.app.ToDo.controller;

import com.app.ToDo.dtos.LoginBody;
import com.app.ToDo.dtos.UserDto;
import com.app.ToDo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller responsible for managing user-related operations.
 * Provides endpoints for creating, updating, deleting, retrieving,
 * and authenticating users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final String GET_ALL_USERS_ENDPOINT = "/getAllUsers";
    private static final String GET_USER_BY_EMAIL_ENDPOINT = "{email}";
    private static final String CREATE_USER_ENDPOINT = "/createUser";
    private static final String UPDATE_USER_ENDPOINT = "/update";
    private static final String DELETE_USER_ENDPOINT = "/{userId}";
    private static final String LOGIN_ENDPOINT = "/login";


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user with the provided details.
     *
     * @param user the UserDto object containing user details to be created
     * @return a ResponseEntity containing the created UserDto object
     */
   //Create
   @PostMapping(CREATE_USER_ENDPOINT)
    public ResponseEntity<UserDto>  createUser(@RequestBody UserDto user){
       UserDto userDto = this.userService.createUser(user);
       return ResponseEntity.ok(userDto);
   }

   /**
    * Updates an existing user with the provided details.
    *
    * @param user the UserDto object containing updated user details
    * @param userId the ID of the user to be updated
    * @return a ResponseEntity containing the updated UserDto object
    */
   //Update
   @PostMapping(UPDATE_USER_ENDPOINT)
   public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, Long userId){
       UserDto userDto = this.userService.updateUser(user, userId);
       return ResponseEntity.ok(userDto);
   }

   /**
    * Deletes a user based on the provided user ID.
    *
    * @param userId the ID of the user to be deleted
    * @return a ResponseEntity containing a success message after the user is deleted
    */
   //Deleted
   @DeleteMapping(DELETE_USER_ENDPOINT)
   public ResponseEntity<String> deletedUser(@PathVariable Long userId){
       this.userService.deletedUser(userId);
       return ResponseEntity.ok("User deleted successfully!");
   }

   /**
    * Retrieves user details based on the provided email address.
    *
    * @param email the email address of the user to be retrieved
    * @return a ResponseEntity containing the UserDto object of the user with the specified email
    */
   //get user by email
    @GetMapping(GET_USER_BY_EMAIL_ENDPOINT)
    public ResponseEntity<UserDto> getUserByEmail( @PathVariable String email){
        UserDto userDto = this.userService.getUser(email);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a ResponseEntity containing a list of UserDto objects
     *         representing all users.
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
     * Authenticates a user based on the provided login details.
     *
     * @param loginBody an object containing the user's email and password for authentication
     * @return a ResponseEntity containing the authenticated UserDto object if successful,
     *         or an unauthorized response if authentication fails
     */
    @PostMapping(LOGIN_ENDPOINT)
    public ResponseEntity<UserDto> userLogin(@RequestBody LoginBody loginBody){
        UserDto apiRes = new UserDto();
        try {
            apiRes = this.userService.userLogin(loginBody.getEmail(), loginBody.getPassword());
//            apiRes.setMessage("Authentication successful!");
            return ResponseEntity.ok(apiRes);
        } catch (Exception e) {
            return new ResponseEntity<>(apiRes, HttpStatus.UNAUTHORIZED);
        }
    }
}
