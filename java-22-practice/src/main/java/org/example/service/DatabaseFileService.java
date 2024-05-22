package org.example.service;

import org.example.entity.Group;
import org.example.entity.Student;
import org.example.repository.GroupRepository;
import org.example.repository.StudentRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@ManagedResource(objectName = "org.example.service:type=DatabaseFileService")
public class DatabaseFileService {

    private static final String DIRECTORY_PATH = "data";

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Scheduled(fixedRate = 300000) // 30 минут
    @ManagedOperation
    @Transactional
    public void cleanAndWriteFiles() {
        try {
            cleanDirectory();
            writeGroupDataToFile();
            writeStudentDataToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cleanDirectory() throws IOException {
        Files.createDirectories(Paths.get(DIRECTORY_PATH));
        Files.walk(Paths.get(DIRECTORY_PATH))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    private void writeGroupDataToFile() throws IOException {
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            Hibernate.initialize(group.getStudents());
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(DIRECTORY_PATH + "/groups.json"), groups);
    }

    private void writeStudentDataToFile() throws IOException {
        List<Student> students = studentRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(DIRECTORY_PATH + "/students.json"), students);
    }
}
