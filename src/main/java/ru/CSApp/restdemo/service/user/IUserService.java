package ru.CSApp.restdemo.service.user;

import ru.CSApp.restdemo.model.user.User;
import ru.CSApp.restdemo.model.user.UserDto;

import java.util.List;

public interface IUserService {
    public List<User> getAllUsers();
    public User getUserById(Integer id);
    public User getUserByName(String name);
    public User getUserByNameAndPassword(String name, String password);

    public User createUser(UserDto userDto);
    public User updateUser(UserDto userDto);

    public void deleteUserById(Integer Id);

}
