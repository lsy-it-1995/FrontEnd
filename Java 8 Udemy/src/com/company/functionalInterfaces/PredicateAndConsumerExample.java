package com.company.functionalInterfaces;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateAndConsumerExample {
    Predicate<Student> p1 = (s) -> s.getGradeLevel() >= 3;
    Predicate<Student> p2 = (s) -> s.getGpa() >= 3.9;

    BiConsumer<String, List<String>> studentBiConsumer = (name, activies) -> System.out.println(name + ": " + activies);
    BiPredicate<Integer, Double> biPredicate = (gradelevel, grade) -> gradelevel >= 3 && grade >= 3.9;
    Consumer<Student> consumer = (student) ->{
          if(p1.and(p2).test(student)) {
              studentBiConsumer.accept(student.getName(), student.getActivities());
          }
    };
    Consumer<Student> consumer1 = (student) -> {
        if(biPredicate.test(student.getGradeLevel(), student.getGpa())){
            studentBiConsumer.accept(student.getName(), student.getActivities());
        }
    };

    public void printNameAndActivity(List<Student> students){
        System.out.println("Consumer");
        students.forEach(consumer);
    }
    public void printNameAndActivity1(List<Student> students){
        System.out.println("BiPredicate");
        students.forEach(consumer1);
    }
    public static void main(String[] args) {
        List<Student> student = StudentDataBase.getAllStudents();
        new PredicateAndConsumerExample().printNameAndActivity(student);
        new PredicateAndConsumerExample().printNameAndActivity1(student);
    }
}
