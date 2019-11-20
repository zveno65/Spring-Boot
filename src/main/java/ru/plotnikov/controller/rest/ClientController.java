package ru.plotnikov.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.plotnikov.exception.UserAccountException;
import ru.plotnikov.model.User;
import ru.plotnikov.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class ClientController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/usersRest")
    public List<User> users() {
        return userService.findAll();
    }


    @PostMapping("/newRest")
    public User addUser(@RequestBody User user) throws UserAccountException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return userService.findByName(user.getName());
    }

    @PostMapping("/editRest")
    public User edit(@RequestBody User editUser) {
        User user = userService.findById(Long.valueOf(editUser.getId()));
        editUser.setRoles(user.getRoles());
        userService.updateUser(editUser);
        return user;
    }
}
