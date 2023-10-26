package com.javaproject1.petstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.javaproject1.petstore.entities.Product;

import jakarta.annotation.security.RolesAllowed;

@Repository
public interface ProductRepositry extends JpaRepository <Product, Integer> {
   //select * from product where price =500
   List<Product> findByPrice(Double price);

   //select * from product where price>500
   @Secured("ROLE_SALES")
   @RestResource(path = "pricegreater") //insead of long URL use this shortcut 
   List<Product>findByPriceGreaterThan(Double price);

   //select * from product where price<500
   @RolesAllowed("IT")
   @RestResource(path = "priceless")
   List<Product>findByPriceLessThan(Double price);
   // http://localhost:8080/products/search/priceless?price=500

   
   //select * from product where price between 100 and 500
   @PreAuthorize("hasRole('HR')")
   List<Product>findByPriceBetween(Double low,Double high);
   // http://localhost:8080/products/search/findByPriceBetween?low=100&high=500
}
