package com.company.numericStream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class numericStreamBoxingUnboxingExample {
    public static List<Integer> boxing(){
        return IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
    }

    public static int unboxing(List<Integer> boxed){
        return boxed.stream().mapToInt(Integer::intValue).sum();
    }
    public static void main(String[] args) {
        System.out.println(boxing());
        List<Integer> boxingInteger = boxing();
        System.out.println(unboxing(boxingInteger));
    }
}
