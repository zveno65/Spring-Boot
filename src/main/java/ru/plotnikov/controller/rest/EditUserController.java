package ru.plotnikov.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.plotnikov.model.User;
import ru.plotnikov.service.UserService;

import java.util.List;

@RestController
public class EditUserController {
    @Autowired
    private UserService userService;

    @PostMapping("/editRest")
    public User edit(@RequestBody User editUser) {
        User user = userService.findById(Long.valueOf(editUser.getId()));
        editUser.setRoles(user.getRoles());
        userService.updateUser(editUser);
        return user;
    }
}
