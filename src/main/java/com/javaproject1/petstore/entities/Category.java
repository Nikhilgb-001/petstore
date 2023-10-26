package com.javaproject1.petstore.entities;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    
    @CreatedDate
    @Column(updatable = false)
    private Instant creationTime;

    @LastModifiedDate
    private Instant updationTime;

    @OneToMany(mappedBy = "category") //inverse entity 
    List<Product> products; //there are multiple products so we are creating a list

    // http://localhost:8080/products/56/category main url
    // http://localhost:8080/categories/2 inverse entity id 
    
}
