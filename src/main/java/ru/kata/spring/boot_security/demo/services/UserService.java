package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findByUsername(String username);
    User findById(long id);
    void save(User user, String[] roleNames);
    void delete(long id);
}
