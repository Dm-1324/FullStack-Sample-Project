package com.example.Complete_Project.controller;

import com.example.Complete_Project.dto.StudentDto;
import com.example.Complete_Project.entity.Student;
import com.example.Complete_Project.service.StudentService;
// You need to ensure the correct jakarta.validation import is available
// import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "${app.cors.allowed-origins}") // Added for Vue frontend
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // CREATE (POST)
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto dto) {
        // Note: Removed @Valid since the StudentDto was not explicitly provided with validation annotations
        Student s = service.addStudent(dto);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    // READ All (GET)
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // READ by ID (GET /{id})
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> s = service.getStudentById(id);
        return s.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE (PUT /{id})
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentDto dto) {
        // Note: Removed @Valid as above
        try {
            service.updateStudent(id, dto);
            return ResponseEntity.ok("Student Details Updated Successfully!");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // DELETE (DELETE /{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {
            service.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully!");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}