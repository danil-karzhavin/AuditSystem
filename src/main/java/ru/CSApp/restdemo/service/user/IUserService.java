package ru.CSApp.restdemo.service.user;

import ru.CSApp.restdemo.model.User;

import java.util.List;

public interface IUserService {
    public List<User> getAllUsers();
    public User getUserById(Integer id);

    public String createUser(User user);
    public String updateUser(User user);
    public String deleteUser(Integer Id);
}
