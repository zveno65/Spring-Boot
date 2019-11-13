package ru.plotnikov.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.plotnikov.exception.UserAccountException;
import ru.plotnikov.model.User;
import ru.plotnikov.service.UserService;

@RestController
public class NewUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/newRest")
    public User addUser(@RequestBody User user) throws UserAccountException{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return userService.findByName(user.getName());
    }
}
