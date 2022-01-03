package com.company.numericStream;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class numericStreamExample {
    public static int sum(List<Integer> nums){
        return nums.stream().reduce(0, (x, y) -> x + y);
    }
    public static int sumIntStream(){
        return IntStream.rangeClosed(31,6)
                .sum();
    }
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6);
        System.out.println(sum(nums));
        System.out.println(sumIntStream());

        //IntStream range closed
        System.out.println("int stream rangeclosed");
        IntStream.rangeClosed(1, 50).forEach(val -> System.out.print(val + ", "));
        System.out.println();
        System.out.println("int stream range");
        IntStream.range(1, 50).forEach(val -> System.out.print(val + ", "));

        System.out.println();
        //LongSteram range closed
        System.out.println("long stream rangeclosed");
        LongStream.rangeClosed(1, 50).forEach(val -> System.out.print(val + ", "));
        System.out.println();
        System.out.println("long stream range");
        LongStream.range(1, 50).forEach(val -> System.out.print(val + ", "));
        System.out.println();
        //DoubleStream
        System.out.println("double stream range");
        IntStream.range(1,50).asDoubleStream().forEach(val -> System.out.print(val + ", "));
        System.out.println();
        int sum = IntStream.rangeClosed(1, 50).sum();
        System.out.println("sum: " + sum);

        //max
        OptionalInt optionalIntMax = IntStream.rangeClosed(1, 50).max();
        OptionalLong optionalLongMax = LongStream.rangeClosed(1, 50).max();
        System.out.println(optionalIntMax.isPresent()? optionalIntMax.getAsInt(): 0);
        System.out.println(optionalLongMax.isPresent()? optionalLongMax.getAsLong(): 0);

        //min
        OptionalInt optionalIntMin = IntStream.rangeClosed(1, 50).min();
        OptionalLong optionalLongMin = LongStream.rangeClosed(1, 50).min();
        System.out.println(optionalIntMin.isPresent()? optionalIntMin.getAsInt(): 0);
        System.out.println(optionalLongMin.isPresent()? optionalLongMin.getAsLong(): 0);

        OptionalDouble optionalDouble = IntStream.rangeClosed(1, 50).average();
        System.out.println(optionalDouble.isPresent()? optionalDouble.getAsDouble(): 0);

    }
}
