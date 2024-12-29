package com.app.ToDo.controller;

import com.app.ToDo.dtos.LoginBody;
import com.app.ToDo.dtos.UserDto;
import com.app.ToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
   /**
    * Creates a new user using the provided UserDto object.
    *
    * @param user the UserDto object containing the details of the user to be created
    * @return a ResponseEntity containing the created UserDto object
    */
   //Create
   @PostMapping("/")
    public ResponseEntity<UserDto>  createUser(@RequestBody UserDto user){
       UserDto userDto = this.userService.createUser(user);
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
   @PostMapping("/{userId}")
   public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, @PathVariable Long userId){
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
    * Retrieves the details of a user based on the provided user ID.
    *
    * @param userId the ID of the user to retrieve
    * @return a ResponseEntity containing the UserDto object representing the user's details
    */
   //get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId){
        UserDto userDto = this.userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a ResponseEntity containing an iterable of UserDto objects representing all users
     */
    //get all
    @GetMapping("/")
    public ResponseEntity<Iterable<UserDto>> getAllUsers(){
        Iterable<UserDto> userDto = this.userService.getAllUsers();
        return ResponseEntity.ok(userDto);
    }

    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param loginBody the LoginBody object containing the user's email and password
     * @return a ResponseEntity containing the authenticated UserDto object if successful,
     *         or an unauthorized response if authentication fails
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> userLogin(@RequestBody LoginBody loginBody){
        UserDto apiRes = new UserDto();
        try {
            apiRes = this.userService.userLogin(loginBody.getEmail(), loginBody.getPassword());
            return ResponseEntity.ok(apiRes);
        } catch (Exception e) {
            return new ResponseEntity<UserDto>(apiRes, HttpStatus.UNAUTHORIZED);
        }
    }
}
