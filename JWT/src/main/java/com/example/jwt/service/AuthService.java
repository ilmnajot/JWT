package com.example.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = new ArrayList<>(Arrays.asList(
                new User("username1", passwordEncoder.encode("password1"), new ArrayList<>()),
                new User("username2", passwordEncoder.encode("password2"), new ArrayList<>()),
                new User("username3", passwordEncoder.encode("password3"), new ArrayList<>())
        ));
        for (User user : userList) {
            if (user.getUsername().equals(username)){
                return user;
            }throw new UsernameNotFoundException("user not found");
        }
        return null;
    }
}
