package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student robert = new Student(
                    "Robert",
                    "rmakłowicz@example.com",
                    LocalDate.of(1963, Month.AUGUST, 12)
            );
            Student karol = new Student(
                    "Karol",
                    "kokrasa@example.com",
                    LocalDate.of(1978, Month.MAY, 20)
            );

            repository.saveAll(
                    List.of(robert, karol)
            );
        };
    }
}
