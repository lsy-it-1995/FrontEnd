package com.company.streams;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamsFlatMapExample {
    public static List<String> studentList(){
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }
    //get student activities count
    public static long getActivitiesCount(){
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .distinct()
                .count();
    }
    //sort the order
    public static List<String> orderActivies(){
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static Set<String> studentSet(){
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        System.out.println(studentList());
        System.out.println(studentSet());
        System.out.println(getActivitiesCount());
        System.out.println(orderActivies());

    }
}
