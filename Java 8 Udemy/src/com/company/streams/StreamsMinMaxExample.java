package com.company.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamsMinMaxExample {
    public static int findMax(List<Integer> nums){
        return nums.stream().reduce(0, (a,b) -> a>b?a:b);
    }
    public static Optional<Integer> findMaxOptional(List<Integer> nums){
        return nums.stream().reduce((a,b) -> a>b?a:b);
    }
    public static Optional<Integer> findMinOptional(List<Integer> nums){
        return nums.stream().reduce((a,b)->a<b?a:b);
    }
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        System.out.println(findMax(list));
        List<Integer> emptyList = new ArrayList();
        if(!findMaxOptional(emptyList).isPresent()){
            System.out.println("no numbers in the list");
        }
        if(findMaxOptional(list).isPresent()){
            System.out.println("max number in the list is " + findMaxOptional(list).get());
        }
        System.out.println("||||||||||||||||||");
        if(findMinOptional(list).isPresent()){
            System.out.println("min numbere in the list is " + findMinOptional(list).get());
        }
        if(!findMinOptional(emptyList).isPresent()){
            System.out.println("no nums in the list");
        }
    }
}
