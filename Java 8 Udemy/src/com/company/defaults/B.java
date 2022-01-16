package com.company.defaults;

public interface B extends A{
    default void B(){
        System.out.println("B");
    }
    default void A(){
        System.out.println("A" + B.class);
    }
}
