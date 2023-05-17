package com.example.demo.repositories;

import com.example.demo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

//    select * from student s inner join address a on s.address_id = a.id where a.city = 'Radom'
    List<Student> findByAddressCity(String city);

    @Query("from Student where address.city = :city")
    List<Student> getByAddressCity(String city);

//    @Query("From Student where firstName = :first_n and lastName = :lastName")
//    Student myGetByFirstNameAndLastName(@Param("first_n") String firstName, String lastName);

    @Query("from Student where firstName = ?1 and lastName = ?2")
    Student myGetByFirstNameAndLastName(String firstName, String lastName);

    @Modifying // void or Integer
    @Transactional
    @Query("update Student set firstName = :firstName where id = :id")
    Integer updateFirstName(Long id, String firstName);

    @Modifying
    @Transactional
    @Query("delete from Student where firstName = :firstName")
    Integer deleteByFirstName(String firstName);


}
