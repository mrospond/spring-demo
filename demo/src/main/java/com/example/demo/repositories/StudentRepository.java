package com.example.demo.repositories;

import com.example.demo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);
    Student findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findByFirstNameOrLastName(String firstName, String lastName);

    List<Student> findByFirstNameIn(List<String> firstNames);

//    select * from student where first_name like '%oh%'
    List<Student> findByFirstNameContains(String firstName);

//    select * from student where first_name like 'John%'
    List<Student> findByFirstNameStartsWith(String firstName);

}
