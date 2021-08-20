package com.springsecuritydemo.securitydemo.controller;

import com.springsecuritydemo.securitydemo.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final List<Student> studentList = Arrays.asList(
            new Student(1, "sepehr", "mollaei"),
            new Student(2, "ahmad", "salimi"),
            new Student(3, "ali", "hosseini")
    );

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id){
        return studentList.stream().filter(student -> {
             return student.getId() == id;
        }).findFirst().get();
    }

    @GetMapping("/students")
    public List<Student> getStudentList(){
        return studentList;
    }

    @DeleteMapping("/students/{id}")
    public void getStudent(@PathVariable int id){
        System.out.println(id);
    }

    @PostMapping("/students")
    public String getStudent(){
        return "OK";
    }

}
