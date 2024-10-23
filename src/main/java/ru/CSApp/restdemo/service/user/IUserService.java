package ru.CSApp.restdemo.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import ru.CSApp.restdemo.model.User;

import java.util.List;

public interface IUserService {
    public List<User> getAllUsers();
    public User getUserById(Integer id);
    public User getUserByName(String name);

    public Integer createUser(User user);
    public User updateUser(User user);
    public Integer deleteUserById(Integer Id);

}
