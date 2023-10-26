package com.javaproject1.petstore.entities;

import java.time.Instant;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Name should not be null")
    @NotEmpty(message = "Name should contain data")
    @Length(min=3, max=20,message = "name should be of 3 to 20 char")
    private String name;

    @NotNull(message = "Price should not be null")
    @Min(value = 1,message = "price should not be less than 1")
    @Max(value=100000,message = "price should not be greater than 100000")
    private Double price;

    @NotNull(message = "Dedcrption should not be null")
    @NotEmpty(message = "Description should contain data")
    @Length(min=10, max=100,message = "Description should be of 10 to 100 char")
    private String description;  

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant creationTime;

    @LastModifiedDate
    private Instant updationTime;

    //Entity and Relationship
    @ManyToOne //owner entity
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "products_ordered",
     joinColumns = @JoinColumn(name = "product_id"),
     inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<OrderDetails> orders;

    private String imageUrl;
}