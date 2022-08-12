package com.lsy.spring.data.jpatutorial.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lsy.spring.data.jpatutorial.entity.Guardian;
import com.lsy.spring.data.jpatutorial.entity.Student;


@SpringBootTest
public class StudentRepositoryTest {
    
    @Autowired
    private StudentRepository studentRepository;
     
    @Test
    public void saveStudent(){
        Student student = Student.builder()
                            .emailId("123@gmail.com")
                            .firstName("zzz")
                            .lastName("111")
                            // .guardianName("Nikhil")
                            // .guardianEmail("nikhil@gmail.com")
                            // .guardianMobile("9999999999")
                            .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .email("nikhil@gmail.com")
                .name("Nikhil")
                .mobile("9999956324")
                .build();

        Student student = Student.builder()
                .firstName("Shivam")
                .emailId("shivam@gmail.com")
                .lastName("Kumar")
                .guardian(guardian)
                .build();

        studentRepository.save(student); 
    }

    @Test
    public void printAllStudent(){
        List<Student> list = studentRepository.findAll();
        System.out.println("studentList: " + list);
        
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("shivam");
        System.out.println("Students: " + students);
    }
    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("am");
        System.out.println("Students: " + students);
    }

    
    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = 
                studentRepository.findByGuardianName("Nikhil");
        System.out.println("Students: " + students);
    }


    @Test
    public void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("shabbir@gmail.com");
        System.out.println("student: " + student);
    }

    @Test
    public void printGetStudentByFirstName(){
        String studentFirstName = studentRepository.getStudentByFirstNameByEmailAddress("shabbir@gmail.com");
        System.out.println("student: " + studentFirstName);
    }

    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("shivam@gmail.com");
        System.out.println("Student: " + student);
    }
    
    @Test
    public void printgetStudentByEmailAddressNativeNameParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNameParam("shivam@gmail.com");
        System.out.println("Student: " + student);
    }

    @Test
    public void printUpdateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId("sshabbir","shabbir@gmail.com");
    }
}