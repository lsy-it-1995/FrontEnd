package com.company.streams_terminal;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsPartitioningByExample {
    public static void partitionBy(){
        Predicate<Student> student = s -> s.getGpa() >= 3.9;
        Map<Boolean, List<Student>> map = StudentDataBase.getAllStudents().stream()
                                            .collect(Collectors.partitioningBy(student));
        System.out.println(map);
        Map<Boolean, Set<Student>> map1 = StudentDataBase.getAllStudents().stream().collect(Collectors.partitioningBy(student, Collectors.toSet()));
        System.out.println(map1);
    }
    public static void main(String[] args) {
        partitionBy();
    }
}
