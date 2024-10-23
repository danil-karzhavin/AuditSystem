package ru.CSApp.restdemo.model.response;

public class AuthenticationResponse implements IAuthenticationResponse {
    String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String getJwt() {
        return jwt;
    }
}