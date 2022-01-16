package com.company.defaults;

public interface C extends B{
    default void C(){
        System.out.println("C");
    }
    default void B(){
        System.out.println("B" + C.class);
    }
}
