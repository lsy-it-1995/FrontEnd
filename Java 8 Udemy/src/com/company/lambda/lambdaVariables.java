package com.company.lambda;

import java.util.function.Consumer;

public class lambdaVariables {

    static int j = 1;
    public static void main(String[] args) {
        int i = 3;
        Consumer<Integer> c = (a) ->{
//            i++; not allow to change the local variable
            j++;
            System.out.println(a + j + i);
        };
        c.accept(2);
    }
}
