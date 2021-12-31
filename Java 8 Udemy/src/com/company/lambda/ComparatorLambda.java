package com.company.lambda;

import java.util.Comparator;

public class ComparatorLambda {
    public static void main(String[] args){
        /*
            Prior Java 8
        */
        Comparator<Integer> comparator = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o1.compareTo(o2);
            }
        };
        System.out.println(comparator.compare(1, 0));
        /*
            Java 8
         */

        Comparator<Integer> lambdaComparator = (a, b) -> a.compareTo(b);
        Comparator<Integer> lambdaComparator1 = (Integer a, Integer b) -> a.compareTo(b);
        System.out.println(lambdaComparator.compare(1, 0));
        System.out.println(lambdaComparator1.compare(1, 0));

    }
}
