package ru.plotnikov.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.plotnikov.model.User;
import ru.plotnikov.service.UserService;

import java.util.List;

@RestController
public class ListUsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/usersRest")
    public List<User> users() {
        return userService.findAll();
    }
}
