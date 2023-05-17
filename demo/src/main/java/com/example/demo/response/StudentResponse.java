package com.example.demo.response;

import com.example.demo.entities.Student;
import com.example.demo.entities.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class StudentResponse {

    @JsonIgnore
    private long id;

    @JsonProperty("first_name")
    private String firstName;

    private String lastName;

    private String email;

//    private String fullName;
    private String street;
    private String city;

    private List<SubjectResponse> learningSubjects;


//    public StudentResponse(Student student) {
////        this.id = student.getId();
//        this.firstName = student.getFirstName();
//        this.lastName = student.getLastName();
//        this.email = student.getLastName();
//        this.fullName = student.getFirstName() + " " +
//                student.getLastName();
//    }

    public StudentResponse(Student student) {
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getLastName();

        this.street = student.getAddress().getStreet();
        this.city = student.getAddress().getCity();

        if (student.getLearningSubjects() != null) {
            learningSubjects = new ArrayList<>();
            for (Subject subject: student.getLearningSubjects()) {
                learningSubjects.add(new SubjectResponse(subject));
            }
        }
    }
}
