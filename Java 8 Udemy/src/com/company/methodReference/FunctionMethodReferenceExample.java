package com.company.methodReference;

import java.util.Locale;
import java.util.function.Function;

public class FunctionMethodReferenceExample {

    static Function<String, String> toUpperLambda = (s) -> s.toUpperCase();
    static Function<String, String> toUpperMethodReference = String::toUpperCase;

    public static void main(String[] args) {
        System.out.println(toUpperLambda.apply("abc"));
        System.out.println(toUpperMethodReference.apply("cba"));

    }
}
