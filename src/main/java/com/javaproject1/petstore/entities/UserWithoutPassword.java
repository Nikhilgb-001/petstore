package com.javaproject1.petstore.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userwithoutpassword", types = {User.class})
public interface UserWithoutPassword {
    String getFirstName();
    String getLastName();
    String getEmail();
    String getMobile();
}
