package com.company.streams;

import com.company.data.Student;
import com.company.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamsReduceExample {

    public static  int multiplication(List<Integer> list){
        return list.stream()
                .reduce(1,(a,b) -> a*b);
    }
    public static Optional<Integer> multiplicatitonWithoutIdentity(List<Integer> list){
        return list.stream()
                .reduce((a,b)->a*b);
    }

    public static Optional<Student> getHighestGPA(){
        Optional<Student> goodStudent = StudentDataBase.getAllStudents().stream()
                                         .reduce((s1, s2) -> (s1.getGpa() > s2.getGpa()? s1: s2));
        return goodStudent;
    }
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4,5);
        System.out.println(multiplication(nums));
        Optional<Integer> val = multiplicatitonWithoutIdentity(nums);
        if(val.isPresent()){
            System.out.println(val.get());
        }
        Optional<Student> students = getHighestGPA();
        if(students.isPresent()){
            System.out.println(students.get());
        }
    }
}
