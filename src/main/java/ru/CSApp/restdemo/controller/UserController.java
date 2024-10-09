package ru.CSApp.restdemo.controller;

import ru.CSApp.restdemo.model.User;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.service.user.IUserService;
import ru.CSApp.restdemo.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    IUserService userService;

    UserController(UserService userService){
        this.userService = userService;
    };

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserDetails(@PathVariable("userId") Integer userId){
        return ResponseHandler.responseBuilder("Requested User Details are given here",
                HttpStatus.OK, userService.getUserById(userId));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers(){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, userService.getAllUsers());
    }

    @PostMapping("/")
    public ResponseEntity<Object> createUserDetails(@RequestBody User user)
    {
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, userService.createUser(user));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, userService.updateUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") Integer userId){
        return ResponseHandler.responseBuilder("",
                HttpStatus.OK, userService.deleteUserById(userId));
    }
}
