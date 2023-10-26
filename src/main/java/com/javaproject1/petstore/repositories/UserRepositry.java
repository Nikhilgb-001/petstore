package com.javaproject1.petstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.javaproject1.petstore.entities.User;
import com.javaproject1.petstore.entities.UserWithoutPassword;

@RepositoryRestResource(excerptProjection = UserWithoutPassword.class)
public interface UserRepositry extends JpaRepository<User, Integer>{

    List<User> findByFirstName(String name);
    // http://localhost:8080/users/search/findByFirstName?name=harry
    
    //select the 
    User findByEmail(String email);
    //http://localhost:8080/users/search/findByEmail?email=harry.potter@gmail.com

    //select * from User where email like '%e%'
    List<User> findByEmailContaining(String pattren); //User is the return type 
    //http://localhost:8080/users/search/findByEmailContaining?pattren=h query to test in postman
}
