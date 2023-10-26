package com.javaproject1.petstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.javaproject1.petstore.entities.RegisteredUser;
import com.javaproject1.petstore.repositories.RegisteredUserRepository;

@Service
public class AuthService {
    @Autowired
    private RegisteredUserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String register(RegisteredUser user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        this.repository.save(user);
        return "User Registered Successfully";
    }
}
