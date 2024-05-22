package org.example.service;

import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    public StudentServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllStudents() {
        // Создание тестовых данных
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("Student 1");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Student 2");

        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));

        // Вызов метода, который мы тестируем
        List<Student> students = studentService.getAllStudents();

        // Проверка результатов
        assertEquals(2, students.size());
        assertEquals("Student 1", students.get(0).getFirstName());
        assertEquals("Student 2", students.get(1).getFirstName());

        // Проверяем, был ли вызван метод findAll у репозитория
        verify(studentRepository, times(1)).findAll();
    }
}
