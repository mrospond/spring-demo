package com.example.demo.response;

import com.example.demo.entities.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentResponse {

    @JsonIgnore
    private long id;

    @JsonProperty("first_name")
    private String firstName;

    private String lastName;

    private String email;

    private String fullName;

    public StudentResponse(Student student) {
//        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getLastName();
        this.fullName = student.getFirstName() + " " +
                student.getLastName();
    }
}
