package com.company.methodReference;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.function.Predicate;

public class RefractorMethodReferenceExample {

    static Predicate<Student> p1 = RefractorMethodReferenceExample::GradeLevelConstraint;

    public static boolean GradeLevelConstraint(Student s){
        return s.getGradeLevel()>=3;
    }

    public static void main(String[] args) {
        System.out.println(p1.test(StudentDataBase.studentSupplier.get()));

    }
}
