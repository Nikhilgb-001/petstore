package com.javaproject1.petstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject1.petstore.entities.User;
import com.javaproject1.petstore.services.UserService;

import jakarta.validation.Valid;

@RestController // retuns values of all the methods are going to be in json format
public class UserController {

    // @Autowired
    // private UserService userService;

    // // CRUD

    // // Creating
    // @PostMapping("/users")
    // // ? indicates that response can be of any type
    // public ResponseEntity<?> add(@RequestBody @Valid User u) {
    //     return ResponseEntity.status(HttpStatus.CREATED)
    //             .body(userService.addUser(u));
    // }

    // // Reading
    // @GetMapping("/users")
    // public ResponseEntity<?> getUsers() {
    //     return ResponseEntity.ok()
    //             .body(userService.getAllUsers());
    // }

    // @GetMapping("/users/{id}")
    // public ResponseEntity<?> getUser(@PathVariable Integer id) {
    //     User found = userService.getUserById(id);
    //     if (found == null) {
    //         return ResponseEntity.badRequest()
    //                 .body("User with this id does not exists");
    //     } else {
    //         return ResponseEntity.ok()
    //                 .body(found);
    //     }
    // }

    // // Updating
    // @PutMapping("/users/{id}")
    // public ResponseEntity<?> update(@PathVariable Integer id,
    //         @RequestBody User u) {
    //     User found = userService.getUserById(id);
    //     if (found == null) {
    //         return ResponseEntity.badRequest()
    //                 .body("User with this id does not exists");
    //     } else {
    //         return ResponseEntity.ok()
    //                 .body(userService.updateUser(id, u));
    //     }
    // }

    // // Deleting
    // @DeleteMapping("/users/{id}")
    // public ResponseEntity<?> delete(@PathVariable Integer id) {
    //     User found = userService.getUserById(id);
    //     if (found == null) {
    //         return ResponseEntity.badRequest()
    //                 .body("User with this id does not exists");
    //     } else {
    //         return ResponseEntity.ok()
    //                 .body(userService.deleteUser(id));
    //     }
    // }

}
