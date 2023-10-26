package com.javaproject1.petstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaproject1.petstore.entities.Category;

@Repository
public interface CategoryRepositry extends JpaRepository<Category, Integer>{
    
}
