package ru.CSApp.restdemo.model.user;

import java.time.LocalDate;

public class UserDto {
    public UserDto(Integer id, String lastname, String username, String surname, String password, LocalDate expirationDate) {
        this.id = id;
        this.lastname = lastname;
        this.username = username;
        this.surname = surname;
        this.password = password;
        ExpirationDate = expirationDate;
    }

    Integer id;
    String lastname;
    String username;
    String surname;
    String password;
    LocalDate ExpirationDate;

    public Integer getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getExpirationDate() {
        return ExpirationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        ExpirationDate = expirationDate;
    }
}
