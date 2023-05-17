package com.example.demo.entities;


import com.example.demo.request.CreateStudentRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // not representing any column in the student table
    @Transient
    private String fullName;

    public Student(CreateStudentRequest createStudentRequest) {
        this.firstName = createStudentRequest.getFirstName();
        this.lastName = createStudentRequest.getLastName();
        this.email = createStudentRequest.getEmail();
        this.firstName = createStudentRequest.getFirstName() + " " +
                createStudentRequest.getLastName();
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
