package com.javaproject1.petstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@Data
class Student {
    private int id;
    private String name;
    private String email;

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // public int getId() {
    // return id;
    // }

    // public void setId(int id) {
    // this.id = id;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public String getEmail() {
    // return email;
    // }

    // public void setEmail(String email) {
    // this.email = email;
    // } the lombock auto matically creates getters and setters methods
    // automatically we have to just mention @Data anotation over the class which u
    // have to create
}

@RestController // if we use rest controller there is no need to add @responsebody
public class firstController {
    @RequestMapping("/message")
    // @ResponseBody // convert string to json body
    // we will get white label page error message
    // because controller was excepting the name of the html file that
    // but we don't want send some html file as response
    // rather we want to send the string data embadded as JSON
    public String getMessage() {
        return "Welcome to the first REST API.";
    }

    @RequestMapping("/marks")
    // @ResponseBody
    public Integer getContact() {
        return 3113;
    }

    @RequestMapping("/student")
    // @ResponseBody
    public Student getStudent() {
        return new Student(1, "nikhil", "nikhilgb3113@gmail.com");
    }

    @RequestMapping("/student-list")
    // @ResponseBody
    public List<Student> getStudentList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "gb", "xyz@gmail"));
        students.add(new Student(2, "raji", "yxz@gmail"));
        students.add(new Student(3, "kavya", "zyx@gmail"));
        return students;
    }
}
