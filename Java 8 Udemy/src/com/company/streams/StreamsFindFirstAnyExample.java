package com.company.streams;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.Optional;

public class StreamsFindFirstAnyExample {
    public static Optional<Student> findFirst(){
        return StudentDataBase.getAllStudents().stream()
                .filter(s -> s.getGpa() >= 3.9)
                .findFirst();
    }

    public static Optional<Student> findAny(){
        return StudentDataBase.getAllStudents().stream()
                .filter(s -> s.getGpa() >= 4.0)
                .findAny();
    }
    public static void main(String[] args) {
        Optional<Student> students = findFirst();
        if(students.isPresent()){
            System.out.println(students.get());
        }else{
            System.out.println("none existed");
        }

        Optional<Student> studentsAny = findAny();
        if(studentsAny.isPresent()){
            System.out.println(studentsAny.get());
        }else{
            System.out.println("none existed");
        }
    }
}
