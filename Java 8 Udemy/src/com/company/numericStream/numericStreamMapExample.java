package com.company.numericStream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class numericStreamMapExample {

    //maptoObj
    public static List<Integer> mapToObj(){
        return IntStream.rangeClosed(1, 5).mapToObj(i  -> {
            return new Integer(i);
        }).collect(Collectors.toList());
    }

    //maptoLong
    public static long mapToLong(){
        return IntStream.rangeClosed(1, 5).mapToLong(i -> i).sum();
    }
    //maptoDouble
    public static double mapToDouble(){
        return IntStream.rangeClosed(1, 5).mapToDouble(i -> i).sum();
    }
    public static void main(String[] args) {
        System.out.println(mapToObj());
        System.out.println(mapToLong());
        System.out.println(mapToDouble());
    }
}
