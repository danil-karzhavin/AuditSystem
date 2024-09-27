package com.thinkconstructive.restdemo.controller;

import com.thinkconstructive.restdemo.model.User;
import com.thinkconstructive.restdemo.response.ResponseHandler;
import com.thinkconstructive.restdemo.service.user.IUserService;
import com.thinkconstructive.restdemo.service.user.impl.UserService;
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
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public String createUserDetails(@RequestBody User user)
    {
        userService.createUser(user);
        return "User Created Successfully";
    }

    @PutMapping("/")
    public String updateUser(@RequestBody User user){
        userService.updateUser(user);
        return "User Updated Successfully";
    }


}
