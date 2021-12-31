package com.company.functionalInterfaces;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.function.Predicate;

public class PredicateStudentExample {
    static Predicate<Student> p1 = (s) -> s.getGradeLevel() >=3;
    static Predicate<Student> p2 = (s) -> s.getGpa() >= 3.9;
    static List<Student> students = StudentDataBase.getAllStudents();

    public static void filterStudentByGradeLevel(){
        System.out.println("filterStudentByGradeLevel");
        students.forEach((student)->{
            if(p1.test(student)){
                System.out.println(student);
            }
        });
    }

    public static void filterStudentByGrade(){
        System.out.println("filterStudentByGrade");
        students.forEach((student)->{
            if(p2.test(student)){
                System.out.println(student);
            }
        });
    }

    public static void filterStudent(){
        System.out.println("filterStudent");
        students.forEach((student)->{
            if(p1.and(p2).test(student)){
                System.out.println(student);
            }
        });
    }
    public static void main(String[] args) {
        filterStudentByGradeLevel();
        filterStudentByGrade();
        filterStudent();
    }
}
