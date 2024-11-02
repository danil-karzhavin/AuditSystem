package ru.CSApp.restdemo.service.user;

import ru.CSApp.restdemo.exception.user.UserNotFoundException;
import ru.CSApp.restdemo.model.user.User;
import ru.CSApp.restdemo.model.user.UserDto;
import ru.CSApp.restdemo.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            throw new UserNotFoundException("Not found user with such Id");
        return userRepository.findById(userId).get();
    }

    @Override
    public User getUserByName(String name) {
        User user = userRepository.findByUsername(name);
        if(user == null)
            throw new UserNotFoundException("Not found user with such name");
        return user;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        User user = userRepository.findByUsernameAndPassword(name, password);
        if(user == null)
            throw new UserNotFoundException("Not found user with such name and password");
        return user;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();

        Optional.ofNullable(userDto.getLastname()).ifPresent(user::setLastname);
        Optional.ofNullable(userDto.getUsername()).ifPresent(user::setUsername);
        Optional.ofNullable(userDto.getSurname()).ifPresent(user::setSurname);
        Optional.ofNullable(userDto.getPassword()).ifPresent(user::setPassword);
        Optional.ofNullable(userDto.getExpirationDate()).ifPresent(user::setExpirationDate);

        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(UserDto userDto) {
        if(userRepository.findById(userDto.getId()).isEmpty())
            throw new UserNotFoundException("Not found user with such Id");
        User user = userRepository.findById(userDto.getId()).get();

        Optional.ofNullable(userDto.getLastname()).ifPresent(user::setLastname);
        Optional.ofNullable(userDto.getUsername()).ifPresent(user::setUsername);
        Optional.ofNullable(userDto.getSurname()).ifPresent(user::setSurname);
        Optional.ofNullable(userDto.getPassword()).ifPresent(user::setPassword);
        Optional.ofNullable(userDto.getExpirationDate()).ifPresent(user::setExpirationDate);

        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUserById(Integer userId) {
        if(userRepository.findById(userId).isEmpty())
            throw new UserNotFoundException("Not found user with such Id");
        userRepository.deleteById(userId);
    }
}