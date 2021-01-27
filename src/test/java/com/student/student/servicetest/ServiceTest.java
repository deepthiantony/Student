package com.student.student.servicetest;

import com.student.student.exception.StudentServiceException;
import com.student.student.model.Student;
import com.student.student.repository.StudentRepository;
import com.student.student.service.StudentService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Autowired
    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @BeforeEach()
    public void init(){
        studentService.setStudentRepository(studentRepository);
        List<Student> students=new ArrayList<>();
        Calendar cal=Calendar.getInstance();
        Student st=new Student(1L,"Rachel",2,cal.getTime(),null);
        students.add(st);
        Mockito.when(studentRepository.findAll()).thenReturn(students);
    }

    @Test
    public void testGetStudents() throws StudentServiceException {
        List<Student> students = studentService.getStudent();
        Assertions.assertNotNull(students);

    }

}
