package com.example.demo.services;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.InQueryRequest;
import com.example.demo.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);

        student = studentRepository.save(student);
        return student;
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();

        if (updateStudentRequest.getFirstName() != null &&
                !updateStudentRequest.getFirstName().isEmpty()) {
            student.setFirstName(updateStudentRequest.getFirstName());
        }

        student = studentRepository.save(student);

        return student;
    }

    public String deleteStudent(long id) {

        if(studentRepository.findById(id).isEmpty()) return String.format("Student with id %s doesn't exist", id);

        studentRepository.deleteById(id);

        return "Student deleted successfully";
    }

    public List<Student> getByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public Student getByFirstNameAndLastName(String firstName, String lastName) {
//        JPA Repository
//        return studentRepository.findByFirstNameAndLastName(firstName, lastName);

//        JPQL
        return studentRepository.myGetByFirstNameAndLastName(firstName, lastName);
    }

    public List<Student> getByFirstNameOrLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest) {
        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
    }

    public List<Student> getAllStudentsWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getAllStudentsWithSorting() {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName");

        return studentRepository.findAll(sort);
    }

//    like query
    public List<Student> like(String firstName) {
        return studentRepository.findByFirstNameContains(firstName);
    }

    public List<Student> startsWith(String firstName) {
        return studentRepository.findByFirstNameStartsWith(firstName);
    }

    public Integer updateStudentWithJpql(long id, String firstName) {
        return studentRepository.updateFirstName(id, firstName);
    }

    public Integer deleteStudent(String firstName) {
        return studentRepository.deleteByFirstName(firstName);
    }
}
