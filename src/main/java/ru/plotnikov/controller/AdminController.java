package ru.plotnikov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.plotnikov.exception.UserAccountException;
import ru.plotnikov.model.User;
import ru.plotnikov.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/new")
    public String addUser(User user, Model model) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addUser(user);
        }
        catch (UserAccountException exc) {
            model.addAttribute("message", "This user exists!");
            return "registration";
        }

        try {
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        }
        catch (Exception e) {
            return "list";
        }
        return "redirect:/admin";
    }

}
