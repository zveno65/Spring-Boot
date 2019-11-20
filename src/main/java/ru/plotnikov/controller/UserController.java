package ru.plotnikov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.plotnikov.model.User;
import ru.plotnikov.service.UserService;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String editForm(@RequestParam(required = false) Long id, Model model) {
        boolean isAdmin = false;
        AbstractAuthenticationToken authentication = null;
        authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        User user = null;

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            user = (User) authentication.getPrincipal();
        }
        else
            user = userService.findByName((String) authentication.getPrincipal());

        if (id==null || !isAdmin && user.getId()!=Long.valueOf(id)) {
            return "redirect:/user?id=" + user.getId();
        }

        user = userService.findById(Long.valueOf(id));
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping
    public String editUser(@ModelAttribute("user") User editUser) {
        User user = userService.findById(Long.valueOf(editUser.getId()));
        editUser.setRoles(user.getRoles());
        userService.updateUser(editUser);
        return "redirect:/admin";
    }

}