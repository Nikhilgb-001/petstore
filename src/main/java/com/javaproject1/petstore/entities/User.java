package com.javaproject1.petstore.entities;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.javaproject1.petstore.validators.Mobile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// @Data
// @Entity //will create table 
// @EntityListeners(AuditingEntityListener.class)
// public class User 
// {
//     @Id // one field to be declare primary (key) islye we use @id to make id field primary
//     @GeneratedValue(strategy = GenerationType.AUTO) //generate unique id automatically
//     private Integer id;

//     @NotNull(message ="First Name is required")
//     @NotEmpty(message = "First Name is empty")
//     @Column(name = "fname", nullable = false) // it should not be null

//     private String firstName;

//     @NotEmpty(message = "last name is empty")
//     @Column(name = "lname",nullable = false)
//     private String lastName;

//     @NotNull(message = "Email is required")
//     @Email(message = "Email is not well formed")
//     @NotNull(message = "Email is empty")
//     @Column(unique = true) // values should be unique
//     private String email;

// //custom validation 

//     @Mobile
//     private String mobile;
//     private String password;
//     @Transient // It will ignore confirm password field in table
//     private String confirmPassword;    

//     @CreatedDate
//     private Instant creationTime;
//     @LastModifiedBy
//     private Instant updationTime;

//     //@join annotation indicates tht we have selected user entity as the owner entity
//     @JoinColumn(name = "addr_id")
//     @OneToOne
//     private Address userAddress;
// }

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id // one field to be declare primary (key) islye we use @id to make id field
        // primary
    @GeneratedValue(strategy = GenerationType.AUTO) // generate unique id automatically
    private Integer id;

    @NotEmpty(message = "first name is empty")
    @Column(name = "fname", nullable = false)
    private String firstName;

    @NotEmpty(message = "last name is empty")
    @Column(name = "lname", nullable = false)
    private String lastName;

    @NotNull(message = "Email is required")
    @Email(message = "Email is not well formed")
    @NotNull(message = "Email is empty")
    @Column(unique = true) // values should be unique
    private String email;

    // custom validation

    @Mobile
    private String mobile;
    private String password;
    
    @Transient // It will ignore confirm password field in table it will not add in the table
    private String confirmPassword;

    @CreatedDate
    private Instant creationTime;
    @LastModifiedBy
    private Instant updationTime;

    //Relationships And Mapings
    @OneToOne
    @JoinColumn(name = "addr_id")
    private Address userAddress;
    // http://localhost:8080/users/id of the user/userAddress in main url
    // http://localhost:8080/addresses/id of the address table in body part of the postman
}
