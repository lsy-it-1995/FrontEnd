package com.company.functionalInterfaces;


import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {

    static Consumer<Student> c1 = p -> System.out.println(p);
    static Consumer<Student> c2 = p -> System.out.println(p.getName().toUpperCase());
    static Consumer<Student> c3 = p -> System.out.println(p.getActivities());

    public static void main(String[] args) {
        Consumer<String> students = (s) -> System.out.println(s.toUpperCase());
        students.accept("java 8");
        printName();
        printNameAndActivity();
        printNameAndActivitiesUsingCondition();
    }

    public static void printName(){
        System.out.println("Print Name");
        List<Student> students = StudentDataBase.getAllStudents();
        students.forEach(c1);
   }

   public static void printNameAndActivity(){
       System.out.println("Print name and activity");
       List<Student> students = StudentDataBase.getAllStudents();
       students.forEach(c1.andThen(c2));
   }

   public static void printNameAndActivitiesUsingCondition(){
       System.out.println("Print name and activity using condition");
       List<Student> students = StudentDataBase.getAllStudents();
       students.forEach((s) ->{
           if(s.getGradeLevel() >= 3 && s.getGpa() > 3.9){
               c1.andThen(c2).accept(s);
           }
       });
   }

}
