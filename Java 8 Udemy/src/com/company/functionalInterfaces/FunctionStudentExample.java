package com.company.functionalInterfaces;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FunctionStudentExample {
    static Function<List<Student>, Map<String, Double>> studentFunction = (students -> {
        Map<String, Double> studentGrade = new HashMap();
        students.forEach(student -> {
            studentGrade.put(student.getName(), student.getGpa());
        });
        return studentGrade;
    });

    static Function<List<Student>, Map<String, Double>> studentFunction1 = (students -> {
        HashMap<String, Double> map = new HashMap();
        students.forEach(student -> {
            if(PredicateStudentExample.p1.test(student)){
                map.put(student.getName(), student.getGpa());
            }
        });
        return map;
    });
    public static void main(String[] args) {
        System.out.println(studentFunction.apply(StudentDataBase.getAllStudents()));
        System.out.println(studentFunction1.apply(StudentDataBase.getAllStudents()));
    }
}
