package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void removeUser(Long id);
    void update(User user);
    List<User> getAllUsers();
    List<Role> listRoles();
    User findByUsername(String username);
    User getUserById(Long id);
}
