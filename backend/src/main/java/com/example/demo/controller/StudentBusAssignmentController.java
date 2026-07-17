package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Student_Bus_Assignment;
import com.example.demo.service.StudentBusAssignmentService;

@RestController
@RequestMapping("/studentbusassignment")
@CrossOrigin(origins = "*")
public class StudentBusAssignmentController {

    @Autowired
    private StudentBusAssignmentService studentBusAssignmentService;

    // Save Student Bus Assignment
    @PostMapping("/save")
    public Student_Bus_Assignment saveStudentBusAssignment(
            @RequestBody Student_Bus_Assignment studentBusAssignment) {

        return studentBusAssignmentService.saveStudentBusAssignment(studentBusAssignment);
    }

    // Get All Student Bus Assignments
    @GetMapping("/getall")
    public List<Student_Bus_Assignment> getAllStudentBusAssignments() {

        return studentBusAssignmentService.getAllStudentBusAssignments();
    }

    // Get Student Bus Assignment By Id
    @GetMapping("/get/{id}")
    public Optional<Student_Bus_Assignment> getStudentBusAssignmentById(
            @PathVariable Long id) {

        return studentBusAssignmentService.getStudentBusAssignmentById(id);
    }

    // Update Student Bus Assignment
    @PutMapping("/update/{id}")
    public Student_Bus_Assignment updateStudentBusAssignment(
            @PathVariable Long id,
            @RequestBody Student_Bus_Assignment studentBusAssignment) {

        return studentBusAssignmentService.updateStudentBusAssignment(id, studentBusAssignment);
    }

    // Delete Student Bus Assignment
    @DeleteMapping("/delete/{id}")
    public String deleteStudentBusAssignment(@PathVariable Long id) {

        studentBusAssignmentService.deleteStudentBusAssignment(id);
        return "Student Bus Assignment Deleted Successfully";
    }

}