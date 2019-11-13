package ru.plotnikov.service;

import ru.plotnikov.exception.UserAccountException;
import ru.plotnikov.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByName(String name);
    void addUser(User user) throws UserAccountException;
    void updateUser(User user);
    void deleteUser(User user);
}
