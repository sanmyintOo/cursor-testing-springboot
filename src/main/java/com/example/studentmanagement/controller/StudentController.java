package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Student Management", description = "APIs for managing students")
@SecurityRequirement(name = "bearerAuth")
public class StudentController {
    private static final Logger logger = LogManager.getLogger(StudentController.class);
    private final StudentRepository studentRepository;

    @GetMapping
    @Operation(summary = "Get all students")
    public ResponseEntity<List<Student>> getAllStudents() {
        logger.info("Fetching all students");
        List<Student> students = studentRepository.findAll();
        logger.debug("Found {} students", students.size());
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        logger.info("Fetching student with id: {}", id);
        return studentRepository.findById(id)
                .map(student -> {
                    logger.debug("Found student: {}", student);
                    return ResponseEntity.ok(student);
                })
                .orElseGet(() -> {
                    logger.warn("Student not found with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        logger.info("Creating new student: {}", student);
        if (studentRepository.existsByEmail(student.getEmail())) {
            logger.warn("Student with email {} already exists", student.getEmail());
            return ResponseEntity.badRequest().build();
        }
        Student savedStudent = studentRepository.save(student);
        logger.debug("Created student: {}", savedStudent);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing student")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        logger.info("Updating student with id: {}", id);
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    student.setId(id);
                    Student updatedStudent = studentRepository.save(student);
                    logger.debug("Updated student: {}", updatedStudent);
                    return ResponseEntity.ok(updatedStudent);
                })
                .orElseGet(() -> {
                    logger.warn("Student not found with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a student")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        logger.info("Deleting student with id: {}", id);
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    logger.debug("Deleted student with id: {}", id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> {
                    logger.warn("Student not found with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }
} 