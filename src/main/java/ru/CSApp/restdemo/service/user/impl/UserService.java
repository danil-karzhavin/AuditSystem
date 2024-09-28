package ru.CSApp.restdemo.service.user.impl;

import ru.CSApp.restdemo.model.User;
import ru.CSApp.restdemo.repository.user.UserRepository;
import ru.CSApp.restdemo.service.user.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService {
    UserRepository userRepository;

    UserService(UserRepository contractRepository){
        this.userRepository = contractRepository;
    }

    @Override
    public List<User> getAllUsers() {
        var users = userRepository.findAll();
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String updateUser(User user) {
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String deleteUser(Integer id) {
        userRepository.deleteById(id);
        return "Success";
    }
}