package ru.CSApp.restdemo.repository.user;

import ru.CSApp.restdemo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String name);
    User findByUsernameAndPassword(String username, String password);
}
