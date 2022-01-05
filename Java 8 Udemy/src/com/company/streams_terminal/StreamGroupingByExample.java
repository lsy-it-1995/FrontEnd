package com.company.streams_terminal;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamGroupingByExample {
    public static void groupByGender() {
        Map<String, List<Student>> map = StudentDataBase.getAllStudents().stream()
                .collect(Collectors.groupingBy(Student::getGender));
        System.out.println(map);
    }

    public static void groupByGPA(){
        Map<String, List<Student>> map = StudentDataBase.getAllStudents().stream()
                                            .collect(Collectors.groupingBy(s->s.getGpa()>=3.9?"Outstanding":"Avg"));
        System.out.println(map);
    }

    public static void twoLevelGrouping(){
        Map<Integer, Map<String, List<Student>>> map = StudentDataBase.getAllStudents().stream()
                                                        .collect(Collectors.groupingBy(Student::getGradeLevel,
                                                                 Collectors.groupingBy(s -> s.getGpa()>=3.9?"Outstanding":"Avg")));
        System.out.println(map);
    }
    public static void twoLevelGrouping_2(){
        Map<String, Integer> map = StudentDataBase.getAllStudents().stream()
                                           .collect(Collectors.groupingBy(Student::getName,
                                           Collectors.summingInt(Student::getNoteBooks)));
        System.out.println(map);
    }

    public static void threeLevelGrouping_3(){
        Map<String, Set<Student>> set = StudentDataBase.getAllStudents()
                                        .stream()
                                        .collect(Collectors.groupingBy(Student::getName,
                                        LinkedHashMap::new, Collectors.toSet()));
        System.out.println(set);
    }

    public static void calculateTopGpaForEachGrade(){
        Map<Integer, Optional<Student>> map =StudentDataBase.getAllStudents()
                                                .stream()
                                                .collect(Collectors.groupingBy(Student::getGradeLevel, Collectors.maxBy(Comparator.comparing(Student::getGpa))));
        System.out.println(map);

        Map<String, Student> map1 = StudentDataBase.getAllStudents().stream()
                                    .collect(Collectors.groupingBy(Student::getName,
                                            Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Student::getGpa)), Optional::get)));
        System.out.println(map1);
    }

    public static void calculateMinGpaForEachGrade(){
        Map<Integer, Optional<Student>> map = StudentDataBase.getAllStudents().stream()
                .collect(Collectors.groupingBy(Student::getGradeLevel,
                         Collectors.minBy(Comparator.comparingDouble(Student::getGpa))));
        Stream.of(map).forEach(System.out::println);
        Map<Integer, Student> map1 = StudentDataBase.getAllStudents().stream()
                .collect(Collectors.groupingBy(Student::getGradeLevel,
                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingDouble(Student::getGpa)), Optional::get)));
        Stream.of(map1).forEach(System.out::println);
    }
    public static void main(String[] args) {
//        groupByGender();
//        groupByGPA();
//        twoLevelGrouping();
//        twoLevelGrouping_2();
//        threeLevelGrouping_3();
//        calculateTopGpaForEachGrade();
        calculateMinGpaForEachGrade();
    }

}
