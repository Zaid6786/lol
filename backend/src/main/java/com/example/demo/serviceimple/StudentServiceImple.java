package com.example.demo.serviceimple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImple implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public student saveStudent(student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public student updateStudent(Long id, student student) {

        student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent != null) {

            existingStudent.setName(student.getName());
            existingStudent.setRollNo(student.getRollNo());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setPassword(student.getPassword());
            existingStudent.setDepartment(student.getDepartment());
            existingStudent.setYear(student.getYear());
            existingStudent.setBusPassNumber(student.getBusPassNumber());
            existingStudent.setRouteId(student.getRouteId());
            existingStudent.setBusId(student.getBusId());
            existingStudent.setPhotoUrl(student.getPhotoUrl());

            return studentRepository.save(existingStudent);
        }

        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}