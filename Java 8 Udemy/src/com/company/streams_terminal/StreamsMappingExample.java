package com.company.streams_terminal;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamsMappingExample {
    public static void main(String[] args) {
        List<String> nameList = StudentDataBase.getAllStudents().stream()
                                 .sorted(Comparator.comparing(Student::getName))
                                 .collect(Collectors.mapping(Student::getName, Collectors.toList()));
        System.out.println("name list: " + nameList);

        Set<String> nameSet = StudentDataBase.getAllStudents().stream()
                                .collect(Collectors.mapping(Student::getName, Collectors.toSet()));
        System.out.println("name set: " + nameSet);
    }
}
