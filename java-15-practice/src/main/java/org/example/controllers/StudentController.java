package org.example.controllers;

import org.example.entity.Student;
import org.example.repository.GroupRepository;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        if (student.getGroup() != null && student.getGroup().getId() != null) {
            student.setGroup(groupRepository.findById(student.getGroup().getId()).orElse(null));
        }
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
