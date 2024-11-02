package ru.CSApp.restdemo.model.authenticate;

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
