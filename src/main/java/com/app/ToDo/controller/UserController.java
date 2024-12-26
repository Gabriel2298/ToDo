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
    
   //Create 
   @PostMapping("/")
    public ResponseEntity<UserDto>  createUser(@RequestBody UserDto user){
       UserDto userDto = this.userService.createUser(user);
       return ResponseEntity.ok(userDto);
   }

   //Update
   @PostMapping("/{userId}")
   public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, @PathVariable Long userId){
       UserDto userDto = this.userService.updateUser(user, userId);
       return ResponseEntity.ok(userDto);
   }

   //Deleted
   @DeleteMapping("/{userId}")
   public ResponseEntity<String> deletedUser(@PathVariable Long userId){
       this.userService.deletedUser(userId);
       return ResponseEntity.ok("User deleted successfully!");
   }

   //get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId){
        UserDto userDto = this.userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }

    //get all
    @GetMapping("/")
    public ResponseEntity<Iterable<UserDto>> getAllUsers(){
        Iterable<UserDto> userDto = this.userService.getAllUsers();
        return ResponseEntity.ok(userDto);
    }

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
