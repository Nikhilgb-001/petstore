package com.javaproject1.petstore.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject1.petstore.entities.User;
import com.javaproject1.petstore.repositories.UserRepositry;

@Service
public class UserService {
    // @Autowired
    // public UserRepositry repositry;
    
    // private Map<Integer, User> userMap = new HashMap<>();
    // private AtomicInteger atomic = new AtomicInteger();

    // //CRUD
    // //Creating
    // public User addUser(User newUser) {
    //     // newUser.setId(atomic.incrementAndGet());
    //     // userMap.put(newUser.getId(), newUser);
    //     // return newUser;
    //     return repositry.save(newUser); //adding data into repository and return new user
    // }

    // //Reading
    // public Iterable <User> getAllUsers() {
    //     // return userMap.values();
    //     return repositry.findAll();
    // }


    // public User getUserById(Integer id) {
    //     // return userMap.get(id);
    //     return repositry.findById(id).orElse(null); //return the required data based on the id else return a message not found
    //     // .get(); returns the user obj if record of the user exists in DB, in not found it returns error
    // }

    // //updating 
    // public User updateUser(Integer id, User updatedUser) {
    //     // updatedUser.setId(id);
    //     // userMap.put(id, updatedUser);
    //     // return updatedUser;
    //     updatedUser.setId(id);
    //     return repositry.save(updatedUser);
    // }

    // //Deleting
    // public String deleteUser(Integer id) {
    //     // userMap.remove(id);
    //     repositry.deleteById(id);
    //     return "Deleted Successfully";
    // }

}
