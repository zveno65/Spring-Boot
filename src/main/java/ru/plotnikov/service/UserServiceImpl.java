package ru.plotnikov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.plotnikov.exception.UserAccountException;
import ru.plotnikov.model.User;
import ru.plotnikov.repository.RoleRepository;
import ru.plotnikov.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        for (User user : userRepository.findAll())
            list.add(user);
        return list;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void addUser(User user) throws UserAccountException {
        if (userRepository.findByName(user.getName()) == null) {
            if (user.getName().equals("Fernando"))
                user.getRoles().add(roleRepository.findByName("ROLE_ADMIN"));
            user.getRoles().add(roleRepository.findByName("ROLE_USER"));
            userRepository.save(user);
        }
        else
            throw new UserAccountException();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        user.getRoles().removeAll(user.getRoles());
        userRepository.delete(user);
    }
}
