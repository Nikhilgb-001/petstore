package com.javaproject1.petstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.javaproject1.petstore.entities.OrderDetails;

@RestResource(path = "orderdetails")
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{
    
}
