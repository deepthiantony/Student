package com.student.student.repositorytest;

import com.student.student.model.Student;
import com.student.student.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;

@DataJpaTest
public class RespositoryTest {
   @Autowired
    TestEntityManager testEntityManager;

    @Autowired
   StudentRepository studentRepository;

   @Test
    public void testFindAll(){
       Student student=new Student();
       student.setName("Rachel");
       testEntityManager.persist(student);
       testEntityManager.flush();

       Assertions.assertNotNull(studentRepository);
       Assertions.assertEquals(1, studentRepository.findAll().size());
   }
}
