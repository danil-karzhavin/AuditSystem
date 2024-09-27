package com.thinkconstructive.restdemo.repository.user;

import com.thinkconstructive.restdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
