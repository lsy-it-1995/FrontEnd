package com.company.streams;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamsMapExample {

    public static List<String> studentList() {
        List<String> list = StudentDataBase.getAllStudents().stream()
                            .map(Student::getName)
                            .map(String::toUpperCase)
                            .collect(Collectors.toList());
        return list;
    }

    public static Set<String> studentSet() {
        Set<String> list = StudentDataBase.getAllStudents().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
        return list;
    }

    public static int numsNotebook(){
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getNoteBooks)
                .reduce(0, (a,b) -> a+b);
    }

    public static int numsNotebookFilter(){
        return StudentDataBase.getAllStudents().stream()
                .filter((student) -> student.getGender().equals("female"))
                .filter((student) -> student.getGradeLevel() >= 3)
                .map(Student::getNoteBooks)
                .reduce(0, (a,b) -> a+b);
    }
    public static void main(String[] args) {
        System.out.println(studentList());
        System.out.println(studentSet());
        System.out.println(numsNotebook());
        System.out.println(numsNotebookFilter());
    }
}
