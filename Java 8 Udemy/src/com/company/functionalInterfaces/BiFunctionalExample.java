package com.company.functionalInterfaces;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class BiFunctionalExample {
    static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>> biFunction = (students, studentPredicate) -> {
        Map<String, Double> map = new HashMap();
        students.forEach(student -> {
            if(studentPredicate.test(student)) {
                map.put(student.getName(), student.getGpa());
            }
        });
        return map;
    };

    public static void main(String[] args) {
        System.out.println(biFunction.apply(StudentDataBase.getAllStudents(), PredicateStudentExample.p2));
    }
}
