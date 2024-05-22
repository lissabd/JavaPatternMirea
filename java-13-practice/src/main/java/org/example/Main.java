package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final StudentPropertiesReader studentPropertiesReader;

    @Autowired
    public Main(StudentPropertiesReader studentPropertiesReader) {
        this.studentPropertiesReader = studentPropertiesReader;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        studentPropertiesReader.printStudentDetails();
    }
}
