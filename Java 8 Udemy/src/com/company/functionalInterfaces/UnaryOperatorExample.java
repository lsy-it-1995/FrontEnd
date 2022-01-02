package com.company.functionalInterfaces;

import java.util.function.UnaryOperator;

public class UnaryOperatorExample {

    static UnaryOperator<String> unaryOperator = (s) -> s.concat("deafult");
    public static void main(String[] args) {
        System.out.println(unaryOperator.apply("java 8 "));
    }
}
