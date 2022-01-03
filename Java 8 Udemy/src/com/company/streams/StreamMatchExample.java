package com.company.streams;

import com.company.data.Student;
import com.company.data.StudentDataBase;

public class StreamMatchExample {

    //allMatch
    public static boolean allMatch(){
        return StudentDataBase.getAllStudents().stream()
                .allMatch((student) -> student.getGpa() >= 3);
    }

    //anyMatch
    public static boolean anyMatch(){
        return StudentDataBase.getAllStudents().stream()
                .anyMatch((student) -> student.getGpa() == 4.0);
    }
    //noneMatch
    public static boolean noneMatch(){
        return StudentDataBase.getAllStudents().stream()
                .noneMatch((s) -> s.getGpa() == 4.0);
    }

    public static void main(String[] args) {
        System.out.println(allMatch());
        System.out.println(anyMatch());
        System.out.println(noneMatch());
    }
}
