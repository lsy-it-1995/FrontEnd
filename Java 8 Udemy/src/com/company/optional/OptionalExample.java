package com.company.optional;

import com.company.data.Bike;
import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.Optional;

public class OptionalExample {

    public static String getStudentName(){
//        Student studentName = StudentDataBase.studentSupplier.get();
        Student studentName = null;
        if(studentName == null){
            return null;
        }
        return studentName.getName();
    }
    public static Optional<String> getOptionalStudent(){
//        Optional<Student> student = Optional.ofNullable(StudentDataBase.studentSupplier.get());
        Optional<Student> student = Optional.ofNullable(null);
        if(student.isPresent()){
            return student.map(Student::getName);
        }
        return Optional.empty();
    }

    public static Optional<String> of(){
        return Optional.of("hello");
//        return Optional.of(null);
    }
    public static Optional<String> ofNullable(){
//        return Optional.ofNullable("hello");
        return Optional.ofNullable(null);
    }
    public static Optional<String> empty(){
        return Optional.empty();
    }

    public static String optionalOrElse(){
//        Optional<Student> student = Optional.ofNullable(StudentDataBase.studentSupplier.get());
        Optional<Student> student = Optional.ofNullable(null);
        String name = student.map(Student::getName).orElse("You");
        return name;
    }
    public static String optionalOrElseGet(){
//        Optional<Student> student = Optional.ofNullable(StudentDataBase.studentSupplier.get());
        Optional<Student> student = Optional.ofNullable(null);
        String name = student.map(Student::getName).orElseGet(()->"you");
        return name;
    }
    public static String optionalorElseThrow(){
//        Optional<Student> student = Optional.ofNullable(StudentDataBase.studentSupplier.get());
        Optional<Student> student = Optional.ofNullable(null);
        String name = student.map(Student::getName).orElseThrow(() -> new RuntimeException("No Data available"));
        return name;
    }
    public static void ifPresent(){
        Optional<String> optional = Optional.ofNullable("hello");
        if(optional.isPresent()){
            System.out.println("is present");
        }
        optional.ifPresent(s -> System.out.println(s));
    }
    public static void optionalFilter(){
        Optional<Student> student = StudentDataBase.getOptionalStudent()
                                    .filter(s -> s.getGpa() >= 3.5);
        student.ifPresent(s-> System.out.println(s));
    }

    public static void optionalMap(){
        Optional<Student> student = StudentDataBase.getOptionalStudent();
        if(student.isPresent()){
            Optional<String> name = student.map(Student::getName);
            System.out.println("name: " + name.get());
        }
    }
    public static void optionalFlatMap(){
        Optional<Student> student = StudentDataBase.getOptionalStudent();
        if(student.isPresent()){
            Optional<String> name = student.filter(s -> s.getGpa()>=3.5)
                                        .flatMap(Student::getBike)
                                        .map(Bike::getName);
            name.ifPresent(s -> System.out.println(s));
        }
    }
    public static void main(String[] args) {
//        System.out.println(getStudentName());
//        Optional<String> student = getOptionalStudent();
//        if(student.isPresent()){
//            System.out.println(student.get());
//        }else{
//            System.out.println("no student");
//        }
//        System.out.println("of: " + of());
//        System.out.println("empty: " + empty());
//        System.out.println("ofNullable: " + ofNullable());
//        System.out.println("optional or else: " + optionalOrElse());
//        System.out.println("optional or else get: " + optionalOrElseGet());
//        System.out.println("optional or else throw: " + optionalorElseThrow());
//        ifPresent();
//        optionalFilter();
//        optionalMap();
        optionalFlatMap();
    }
}
