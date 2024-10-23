package ru.CSApp.restdemo.model.request;

public class AuthenticationRequest implements IAuthenticationRequest {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
