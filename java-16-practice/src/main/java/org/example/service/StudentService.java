package org.example.service;

import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.example.entity.Student;
import org.example.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Student> filterStudents(Map<String, String> filters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        query.select(root);

        Predicate[] predicates = new Predicate[filters.size()];
        int index = 0;
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            predicates[index++] = builder.equal(root.get(fieldName), value);
        }

        query.where(predicates);
        return entityManager.createQuery(query).getResultList();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
