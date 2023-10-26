package com.javaproject1.petstore.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javaproject1.petstore.entities.RegisteredUser;
import com.javaproject1.petstore.repositories.RegisteredUserRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthUserService implements UserDetailsService {
@Autowired
    private RegisteredUserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) 
                throws UsernameNotFoundException {
        RegisteredUser foundUser = this.repository.findByEmail(username);
        if(foundUser == null){
            throw new UsernameNotFoundException("User with this username does not exists");
        }
        else{
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            for(String role: foundUser.getRoles()){
                authorities.add(new SimpleGrantedAuthority(role));
            }

            RegisteredUser user = new RegisteredUser(foundUser.getEmail(), 
                                                foundUser.getPassword(), authorities);

            return user;
        }
    }

}
