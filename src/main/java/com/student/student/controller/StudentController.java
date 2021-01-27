package com.student.student.controller;

import com.student.student.exception.StudentServiceException;
import com.student.student.model.Student;
import com.student.student.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student-api-v1/student/add-student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws StudentServiceException {
        Student studentNew = null;
        studentNew = studentService.addStudent(student);
        return ResponseEntity.ok(studentNew);
    }

    @GetMapping("/student-api-v1/student/get-students")
    public ResponseEntity<List<Student>> getStudent() throws StudentServiceException {
        return ResponseEntity.ok(studentService.getStudent());
    }

    @GetMapping("/student-api-v1/student/get-student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) throws StudentServiceException {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/student-api-v1/student/search/{name}")
    public ResponseEntity<List<Student>> searchSTudentByName(@PathVariable("name") String name, @RequestParam(required = false,defaultValue = "0")
            Integer page, @RequestParam(defaultValue = "10", required = false) Integer size) throws StudentServiceException {
        return ResponseEntity.ok(studentService.searchStudentByName(name, page, size));
    }
}
