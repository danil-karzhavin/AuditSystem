package ru.CSApp.restdemo.repository.user;

import ru.CSApp.restdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String name);
}
