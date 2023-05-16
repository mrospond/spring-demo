package com.example.demo;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student s1 = new Student(1L, "Mak", "Ło", "wicz@mail");
            Student s2 = new Student(2L, "Rob", "Ert", "mak@mail");
            Student s3 = new Student(3L, "Ło", "Wicz", "rob@mail");

            repository.saveAll(
                    List.of(s1, s2, s3)
            );
        };
    }
}
