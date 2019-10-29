package ru.plotnikov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.plotnikov.model.User;
import ru.plotnikov.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        return "redirect:/admin";
    }

}
