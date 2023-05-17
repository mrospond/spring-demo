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
            Student s1 = new Student("Robert", "Makłowicz", "rm@email.com");
            Student s2 = new Student("Karol", "Okrasa", "ko@email.com");
            Student s3 = new Student("Wojciech", "Amaro", "wa@email.com");

            repository.saveAll(
                    List.of(s1, s2, s3)
            );
        };
    }
}
