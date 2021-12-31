package com.company.functionalInterfaces;

import java.util.Locale;
import java.util.function.Function;

public class FunctionExample {

    static Function<String, String> upperCase = (name) -> name.toUpperCase();
    static Function<String, String> addSomeString = (name) -> name.concat("default");
    static Function<String, Integer> strLength = (name) -> name.length();

    public static void main(String[] args) {
        System.out.println("result is: " + upperCase.apply("java 8"));
        System.out.println("result is: " + upperCase.andThen(addSomeString).apply("java 8"));
        System.out.println("result is: " + upperCase.compose(addSomeString).apply("java 8"));
        Function<String, String> abc = Function.identity();
        System.out.println(abc.apply("ABc" +
                ""));
    }
}
