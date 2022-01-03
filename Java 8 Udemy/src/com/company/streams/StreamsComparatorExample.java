package com.company.streams;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsComparatorExample {

    public static List<Student> sortByName(){
        return StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }
    public static List<Student> sortByGPA(){
        return StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getGpa))
                .collect(Collectors.toList());
    }

    public static List<Student> sortByGPADesc(){
        return StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        System.out.println("sort by Name");
        sortByName().forEach(System.out::println);
        System.out.println("sort by GPA ascending");
        sortByGPA().forEach(System.out::println);
        System.out.println("sort by GPA descending");
        sortByGPADesc().forEach(System.out::println);
    }
}
