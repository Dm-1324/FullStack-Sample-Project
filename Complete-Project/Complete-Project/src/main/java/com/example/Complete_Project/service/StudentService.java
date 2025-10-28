package com.example.Complete_Project.service;

import com.example.Complete_Project.dto.StudentDto;
import com.example.Complete_Project.entity.Student;
import com.example.Complete_Project.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Student addStudent(StudentDto dto) {
        // Map DTO fields (name, email, age) to a new Student Entity
        Student s = new Student();
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        s.setAge(dto.getAge());
        return repo.save(s);
    }

    // READ All
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // READ by ID
    public Optional<Student> getStudentById(Long id) {
        return repo.findById(id);
    }

    // UPDATE
    public Student updateStudent(Long id, StudentDto dto) {
        // Find existing student or throw exception (similar to your Doctor service)
        Student s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        // Update fields
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        s.setAge(dto.getAge());

        return repo.save(s);
    }

    // DELETE
    public void deleteStudent(Long id) {
        // Check existence before deletion
        repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        repo.deleteById(id);
    }
}