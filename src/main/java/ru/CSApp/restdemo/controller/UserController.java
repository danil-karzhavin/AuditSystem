package ru.CSApp.restdemo.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import ru.CSApp.restdemo.model.user.User;
import ru.CSApp.restdemo.model.authenticate.AuthenticationRequest;
import ru.CSApp.restdemo.model.authenticate.AuthenticationResponse;
import ru.CSApp.restdemo.model.user.UserDto;
import ru.CSApp.restdemo.security.JwtUtil;
import ru.CSApp.restdemo.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    IUserService userService;
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;

    public UserController(IUserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto)
    {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("userId") Integer userId){
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = userService.getUserByNameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
