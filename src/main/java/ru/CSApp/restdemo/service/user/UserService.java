package ru.CSApp.restdemo.service.user;

import io.swagger.models.auth.In;
import ru.CSApp.restdemo.exception.contract.contractStage.ContractStageNotFoundException;
import ru.CSApp.restdemo.exception.contractor.ContractorNotFoundException;
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
        try{
            if(userRepository.findById(userId).isEmpty())
                throw new UserNotFoundException("There is no object with such Id");
            return userRepository.findById(userId).get();
        }
        catch (UserNotFoundException ex){
            return null;
        }
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
    public Integer deleteUserById(Integer userId) {
        try{
            if(userRepository.findById(userId).isEmpty())
                throw new UserNotFoundException("There is no object with such Id");
            userRepository.deleteById(userId);
            return userId;
        }
        catch(UserNotFoundException ex){
            return null;
        }
    }
}