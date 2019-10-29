package ru.plotnikov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.plotnikov.repository.UserRepository;
import ru.plotnikov.model.User;

import java.sql.SQLException;

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = null;
        user = userRepository.findByName(name);
        if (user==null)
            throw new UsernameNotFoundException("user not found");
        return user;
    }
}