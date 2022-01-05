package com.company.streams_terminal;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsMinbyMaxbyExample {

    public static Optional<Student> maxBy(){
        return StudentDataBase.getAllStudents().stream()
                .collect(Collectors.maxBy(Comparator.comparing(Student::getGpa)));
    }
    public static Optional<Student> minBy(){
        return StudentDataBase.getAllStudents().stream()
                .collect(Collectors.minBy(Comparator.comparing(Student::getGpa)));
    }
    public static void main(String[] args) {
        System.out.println(maxBy());
        System.out.println(minBy());
    }
}
