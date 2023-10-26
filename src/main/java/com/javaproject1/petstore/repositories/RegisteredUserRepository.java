package com.javaproject1.petstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.javaproject1.petstore.entities.RegisteredUser;

//disallow the direct call to save method means the post request is disabled
@RestResource(exported = false)
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {
    <S extends RegisteredUser> S save(S entity);   

    RegisteredUser findByEmail(String email);
}
