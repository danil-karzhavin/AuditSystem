package ru.CSApp.restdemo.service.user;

import ru.CSApp.restdemo.exception.user.UserNotFoundException;
import ru.CSApp.restdemo.model.User;
import ru.CSApp.restdemo.repository.user.UserRepository;
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
    public User getUserById(Integer userId) {
        if(userRepository.findById(userId).isEmpty())
            throw new UserNotFoundException("User not found with such Id");
        return userRepository.findById(userId).get();
    }

    @Override
    public User getUserByName(String name) {
        User user = userRepository.findByUsername(name);
        if(user == null)
            throw new UserNotFoundException("User not found with such name");
        return user;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        User user = userRepository.findByUsernameAndPassword(name, password);
        if(user == null)
            throw new UserNotFoundException("User not found with such name and password");
        return user;
    }

    @Override
    public Integer createUser(User user) {
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUserById(Integer userId) {
        if(userRepository.findById(userId).isEmpty())
            throw new UserNotFoundException("User not found with such Id");
        userRepository.deleteById(userId);
    }
}