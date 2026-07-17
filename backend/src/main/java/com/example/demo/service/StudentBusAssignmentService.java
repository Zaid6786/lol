package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Student_Bus_Assignment;

public interface StudentBusAssignmentService {

    Student_Bus_Assignment saveStudentBusAssignment(Student_Bus_Assignment studentBusAssignment);

    List<Student_Bus_Assignment> getAllStudentBusAssignments();

    Optional<Student_Bus_Assignment> getStudentBusAssignmentById(Long id);

    Student_Bus_Assignment updateStudentBusAssignment(Long id, Student_Bus_Assignment studentBusAssignment);

    void deleteStudentBusAssignment(Long id);

}