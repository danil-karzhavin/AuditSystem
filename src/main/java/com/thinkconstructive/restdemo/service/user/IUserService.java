package com.thinkconstructive.restdemo.service.user;

import com.thinkconstructive.restdemo.model.User;

import java.util.List;

public interface IUserService {
    public List<User> getAllUsers();
    public User getUserById(Integer id);

    public String createUser(User user);
    public String updateUser(User user);
    public String deleteUser(Integer Id);
}
