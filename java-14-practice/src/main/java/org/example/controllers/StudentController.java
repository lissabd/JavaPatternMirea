package org.example.controllers;

import org.example.models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>();

    @PostMapping("/create")
    public String createStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String middleName) {
        Student student = new Student(firstName, lastName, middleName);
        students.add(student);
        return "Student created successfully!";
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return students;
    }

    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam int index) {
        if (index >= 0 && index < students.size()) {
            students.remove(index);
            return "Student deleted successfully!";
        } else {
            return "Invalid index!";
        }
    }
}
