package com.example.demo.services;

import com.example.demo.entities.Address;
import com.example.demo.entities.Student;
import com.example.demo.entities.Subject;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.SubjectRepository;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.CreateSubjectRequest;
import com.example.demo.request.InQueryRequest;
import com.example.demo.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        address = addressRepository.save(address);

        student.setAddress(address);
        student = studentRepository.save(student);

        List<Subject> subjectList = new ArrayList<>();

        if(createStudentRequest.getSubjectsLearning() != null) {
            for (CreateSubjectRequest createSubjectRequest : createStudentRequest.getSubjectsLearning()) {
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                subject.setStudent(student);

                subjectList.add(subject);
            }

            subjectRepository.saveAll(subjectList);
        }

        student.setLearningSubjects(subjectList);

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

    public List<Student> getByCity(String city) {
        return studentRepository.getByAddressCity(city);
    }
}
