package com.company.paraalleelStream;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class parallelStreamExample {

    public static long performance(Supplier<Integer> supplier, int loops){
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < loops; i++){
            supplier.get();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    public static int sequential_sum(){
        return IntStream.rangeClosed(1, 100000).sum();
    }
    public static int parallel_sum(){
        return IntStream.rangeClosed(1, 100000).parallel().sum();
    }

    public static void sequentialPrintStudentActivies(){
        long startTime = System.currentTimeMillis();
        List<String> list = StudentDataBase.getAllStudents().stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;
        System.out.println(diff);
    }

    public static void parallelPrintStudentActivies(){
        long startTime = System.currentTimeMillis();
        List<String> list = StudentDataBase.getAllStudents().stream()
                                .parallel()
                                .map(Student::getActivities)
                                .flatMap(List::stream)
                                .distinct()
                                .sorted()
                                .collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
    public static void main(String[] args) {
        System.out.println(performance(parallelStreamExample::sequential_sum, 20));
        System.out.println(performance(parallelStreamExample::parallel_sum, 20));
        sequentialPrintStudentActivies();
        parallelPrintStudentActivies();
    }
}
