package com.company.functionalInterfaces;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SupplierExample {
    static Supplier<Student> studentSupplier = () -> {
        return new Student("Adam",2,3.6, "male",10, Arrays.asList("swimming", "basketball","volleyball"));
    };
    static Supplier<List<Student>> students = () -> StudentDataBase.getAllStudents();
    public static void main(String[] args) {
        Student student = studentSupplier.get();
        System.out.println(student);
        List<Student> studentsList = students.get();
        System.out.println(studentsList);
    }
}
