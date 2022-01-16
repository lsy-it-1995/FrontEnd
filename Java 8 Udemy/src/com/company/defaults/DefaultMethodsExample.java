package com.company.defaults;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class DefaultMethodsExample {
    static Consumer<Student> studentConsumer = s -> System.out.println(s);
    public static void sortDifferent(){
        List<String> stringList  = Arrays.asList("Adam", "Jenny", "Alex","Eric","Mike");
        /*
        Prior java 8
         */
        Collections.sort(stringList);
        System.out.println("sorted list: " + stringList);

        /*
        Java 8
         */
        stringList.sort(Comparator.naturalOrder());
        System.out.println("sorted list: " + stringList);

        stringList.sort(Comparator.reverseOrder());
        System.out.println("reverse order list: " + stringList);
    }
    public static void sortByStudentGpa(List<Student> students){
        Comparator<Student> gpaComparator = Comparator.comparing(Student::getGpa);
        students.sort(gpaComparator);
    }
    public static void sortByStudentName(List<Student> students){
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
        students.sort(nameComparator);
    }
    public static void sortByStudentName_Gpa_Grade(List<Student> students){
        Comparator<Student> name = Comparator.comparing(Student::getName);
        Comparator<Student> gpa = Comparator.comparingDouble(Student::getGpa);
        Comparator<Student> grade = Comparator.comparing(Student::getGradeLevel);
        students.sort(name.thenComparing(gpa).thenComparing(grade));
    }
    public static void sortWithNullValue(List<Student> students){
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
        Comparator<Student> nullComparator = Comparator.nullsFirst(nameComparator);
        students.sort(nullComparator);

    }
    public static void main(String[] args) {
        List<Student> studentList = StudentDataBase.getAllStudents();
        System.out.println("before sorted:");
        studentList.forEach(studentConsumer);

        System.out.println("after sorted:");
//        sortByStudentGpa(studentList);
//        sortByStudentName(studentList);
//        sortByStudentName_Gpa_Grade(studentList);
        sortWithNullValue(studentList);
        studentList.forEach(studentConsumer);

    }
}
