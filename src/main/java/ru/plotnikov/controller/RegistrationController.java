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
import ru.plotnikov.exception.UserAccountException;
import ru.plotnikov.model.User;
import ru.plotnikov.service.UserService;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


    @PostMapping
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
            return "login";
        }
        return "redirect:/admin";
    }

}