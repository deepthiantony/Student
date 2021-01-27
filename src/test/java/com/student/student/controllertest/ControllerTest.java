package com.student.student.controllertest;

import com.student.student.controller.StudentController;
import com.student.student.exception.StudentServiceException;
import com.student.student.model.Student;
import com.student.student.repository.StudentRepository;
import com.student.student.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Autowired
    TestEntityManager entity;

    @Mock
    private StudentService studentService;
    private StudentRepository studentRepository;

    StudentController studentController = new StudentController();

    @BeforeEach
    public void init() throws StudentServiceException {
        List<Student> students = new ArrayList<Student>();
        Calendar cal=Calendar.getInstance();
        Student st=new Student(1L,"Rachel",2,cal.getTime(),null);
        students.add(st);
        studentController.setStudentService(studentService);
//        Mockito.when(studentRepository.findAll()).thenReturn(students);
        Mockito.when(studentService.getStudent()).thenReturn(students);
    }

    @Test
    public void testGetStudents() throws StudentServiceException {
        ResponseEntity<List<Student>> student = studentController.getStudent();
        Assertions.assertNotNull(student);
        Assertions.assertEquals(1,student.getBody().size());
    }

}
