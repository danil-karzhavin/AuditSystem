package com.thinkconstructive.restdemo.model;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String lastname;
    String name;
    String surname;

    String password;

    LocalDate ExpirationDate;

    public User(){};
    public User(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getExpirationDate() {
        return ExpirationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        ExpirationDate = expirationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}