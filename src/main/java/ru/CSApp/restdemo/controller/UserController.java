package ru.CSApp.restdemo.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import ru.CSApp.restdemo.model.User;
import ru.CSApp.restdemo.model.request.AuthenticationRequest;
import ru.CSApp.restdemo.model.response.AuthenticationResponse;
import ru.CSApp.restdemo.response.ResponseHandler;
import ru.CSApp.restdemo.security.JwtUtil;
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
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;

    public UserController(IUserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

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

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }

        final UserDetails userDetails = userService.getUserByNameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
