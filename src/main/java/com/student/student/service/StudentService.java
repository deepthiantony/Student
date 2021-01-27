package com.student.student.service;

import com.student.student.exception.StudentServiceException;
import com.student.student.model.Address;
import com.student.student.model.Student;
import com.student.student.repository.StudentRepository;
import javassist.bytecode.stackmap.BasicBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentRepository studentRepository;

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Method to add student
    public Student addStudent(Student student) throws StudentServiceException {
        Student studentNew;
        if (student == null) {
            LOGGER.error("Student cannot be null");
            throw new StudentServiceException("Student cannot be null,provide valid value");
        }
        try {
            Calendar cal = Calendar.getInstance();
            student.setCreatedDate(cal.getTime());
            List<Address> studentAddress = student.getAddresses();
            studentAddress.forEach(a -> a.setStudent(student));
            studentNew = studentRepository.save(student);
        } catch (Exception e) {
            LOGGER.error("Unable to save student", e);
            throw new StudentServiceException("Unable to save student", e);
        }
        return studentNew;
    }

    public List<Student> getStudent() throws StudentServiceException {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new StudentServiceException("Student list is empty");
        }
        try {
            return students;
        } catch (Exception e) {
            throw new StudentServiceException("Unable to retrieve student list", e);
        }

    }

    public Student getStudentById(Long id) throws StudentServiceException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student;
        if (!optionalStudent.isPresent()) {
            throw new StudentServiceException("No student with particular id exists");
        }
        try {
            student = optionalStudent.get();
            return student;
        } catch (Exception e) {
            throw new StudentServiceException("Unable to retrieve student details", e);
        }
    }

    public List<Student> searchStudentByName(String name, Integer page, Integer size) throws StudentServiceException {

        List<Student> students = studentRepository.findAllByNameIgnoreCase(name, PageRequest.of(page, size, Sort.by("name")));
        if (students.isEmpty()) {
            throw new StudentServiceException("No students with given name");
        }
        try {
            return students;
        } catch (Exception e) {
            throw new StudentServiceException("Unable to retrieve student list with given name", e);
        }
    }
}
