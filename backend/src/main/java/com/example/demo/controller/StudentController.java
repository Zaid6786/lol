package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Save Student
    @PostMapping("/save")
    public student saveStudent(@RequestBody student student) {
        return studentService.saveStudent(student);
    }

    // Get All Students
    @GetMapping("/getall")
    public List<student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get Student By Id
    @GetMapping("/get/{id}")
    public Optional<student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Update Student
    @PutMapping("/update/{id}")
    public student updateStudent(@PathVariable Long id,
                                 @RequestBody student student) {
        return studentService.updateStudent(id, student);
    }

    // Delete Student
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student Deleted Successfully";
    }

}