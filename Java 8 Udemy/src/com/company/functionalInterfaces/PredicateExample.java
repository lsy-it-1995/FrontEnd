package com.company.functionalInterfaces;

import java.util.function.Predicate;

public class PredicateExample {
    static Predicate<Integer> p = (i) ->{return i%2==0;};
    static Predicate<Integer> p1 = (i) -> i%3 == 0;
    static Predicate<Integer> p2 = (i) -> i%5 == 0;

    public static void predicateAnd(){
        System.out.println("Result in predicateAND: " + p1.and(p).test(6));
    }
    public static void predicateOr(){
        System.out.println("Result in predicateOR: " + p1.or(p).test(10));
    }
    public static void predicateNegate(){
        System.out.println("result in predicateNegate: " + p1.and(p2).negate().test(6));
    }
    public static void main(String[] args) {
        System.out.println("result is p: " + p.test(3));
        System.out.println("result is p1: " + p1.test(3));
        predicateAnd();
        predicateOr();
        predicateNegate();
    }

}
