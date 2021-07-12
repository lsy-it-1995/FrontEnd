package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class studentService {

    private final studentRepository studentRepository;

    public studentService(studentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @Autowired
    public List<student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(student student) {
        Optional<student> studentOptional = studentRepository.findstudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(exist){
            studentRepository.deleteById(studentId);
        }else{
            throw new IllegalStateException("student with id " + studentId+" does not existed");
        }
    }

    @Transactional
    public void updateStudent(Long studentId,String name, String email) {

        System.out.println("dsafadsfadsfasdfasf");

        student student = studentRepository.findById(studentId).
                orElseThrow(()->new IllegalStateException(
                "student with id " + studentId+" does not existed")
        );
        if(name!=null && name.length() > 0 && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())){
            Optional<student> studenoptional = studentRepository.findstudentByEmail(email);
            if(studenoptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }
}
