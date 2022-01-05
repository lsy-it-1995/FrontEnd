package com.company.streams_terminal;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.stream.Collectors;

public class StreamsCountingExample {
    public static long count(){
//        return StudentDataBase.getAllStudents().stream()
//                .filter(s -> s.getGpa() >= 3.9)
//                .collect(Collectors.counting());
        return StudentDataBase.getAllStudents().stream()
                .filter(s -> s.getGender() == "male")
                .collect(Collectors.counting());
    }
    public static void main(String[] args) {
        System.out.println(count());
    }
}
