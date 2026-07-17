package com.example.demo.serviceimple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Student_Bus_Assignment;
import com.example.demo.repository.StudentBusAssignmentRepository;
import com.example.demo.service.StudentBusAssignmentService;

@Service
public class StudentBusAssignmentServiceImple implements StudentBusAssignmentService {

    @Autowired
    private StudentBusAssignmentRepository studentBusAssignmentRepository;

    @Override
    public Student_Bus_Assignment saveStudentBusAssignment(Student_Bus_Assignment studentBusAssignment) {
        return studentBusAssignmentRepository.save(studentBusAssignment);
    }

    @Override
    public List<Student_Bus_Assignment> getAllStudentBusAssignments() {
        return studentBusAssignmentRepository.findAll();
    }

    @Override
    public Optional<Student_Bus_Assignment> getStudentBusAssignmentById(Long id) {
        return studentBusAssignmentRepository.findById(id);
    }

    @Override
    public Student_Bus_Assignment updateStudentBusAssignment(Long id,
            Student_Bus_Assignment studentBusAssignment) {

        Student_Bus_Assignment existingAssignment =
                studentBusAssignmentRepository.findById(id).orElse(null);

        if (existingAssignment != null) {

            existingAssignment.setStudentId(studentBusAssignment.getStudentId());
            existingAssignment.setBusId(studentBusAssignment.getBusId());
            existingAssignment.setRouteId(studentBusAssignment.getRouteId());
            existingAssignment.setAssignedDate(studentBusAssignment.getAssignedDate());
            existingAssignment.setIsActive(studentBusAssignment.getIsActive());
            existingAssignment.setCreatedAt(studentBusAssignment.getCreatedAt());

            return studentBusAssignmentRepository.save(existingAssignment);
        }

        return null;
    }

    @Override
    public void deleteStudentBusAssignment(Long id) {
        studentBusAssignmentRepository.deleteById(id);
    }

}