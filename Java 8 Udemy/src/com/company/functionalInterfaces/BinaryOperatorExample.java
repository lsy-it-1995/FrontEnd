package com.company.functionalInterfaces;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorExample {

    static Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
    public static void main(String[] args) {
        BinaryOperator<Integer> multiplicationBinaryOperator = (a, b) -> a*b;
        System.out.println(multiplicationBinaryOperator.apply(3,4));
        BinaryOperator<Integer> addBinaryOperator = (a, b) -> a+b;
        System.out.println(addBinaryOperator.apply(1, 2));

        BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(comparator);
        System.out.println("maxby result is: " + maxBy.apply(3,4));
        BinaryOperator<Integer> minBy = BinaryOperator.minBy(comparator);
        System.out.println("minby result is: " + minBy.apply(3,4));
    }
}
