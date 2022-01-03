package com.company.streams;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsExample {
    public static void main(String[] args) {

        Predicate<Student> studentGradePredicate = (s) -> s.getGradeLevel() >= 3;
        Predicate<Student> studentGPAPredicate = (s) -> s.getGpa() >= 3.9;
        Map<String, List<String>> map = StudentDataBase.getAllStudents().stream()
                                        .filter(studentGradePredicate)
                                        .filter(studentGPAPredicate)
                                        .collect(Collectors.toMap(Student::getName, Student::getActivities));
        System.out.println(map);
    }
}
