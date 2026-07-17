package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.student;

public interface StudentService {

    student saveStudent(student student);

    List<student> getAllStudents();

    Optional<student> getStudentById(Long id);

    student updateStudent(Long id, student student);

    void deleteStudent(Long id);

}