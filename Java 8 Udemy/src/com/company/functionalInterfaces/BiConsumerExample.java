package com.company.functionalInterfaces;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;

public class BiConsumerExample {
    public static void printNameActivity(){
        BiConsumer<String, List<String>> studentBiConsumer = (name, activity) -> System.out.println(name + ": " + activity);
        List<Student> students = StudentDataBase.getAllStudents();
        students.forEach((s) -> studentBiConsumer.accept(s.getName(), s.getActivities()));
    }
    public static void main(String[] args) {
        BiConsumer<String, String> biConsumerString = (a, b) -> {
            System.out.println("a: " + a + ". b: " + b);
        };
        biConsumerString.accept("yo", "what");
        BiConsumer<Integer, Integer> biConsumerMultiplication = (a,b) -> {
            System.out.println("Multiplication: " + (a*b));
        };
        BiConsumer<Integer, Integer> biConsumerDivision = (a,b) ->{
            System.out.println("Division: " + a/b);
        };

        biConsumerMultiplication.andThen(biConsumerDivision).accept(20,4);
        printNameActivity();
    }
}
