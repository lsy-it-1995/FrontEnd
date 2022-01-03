package com.company.streams;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.stream.Collectors;

public class StreamFilterExample {

    public static List<Student> femaleStudent(){
        return StudentDataBase.getAllStudents().stream()
                .filter((student) -> student.getGender().equals("female"))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        femaleStudent().forEach(System.out::println);
    }
}
