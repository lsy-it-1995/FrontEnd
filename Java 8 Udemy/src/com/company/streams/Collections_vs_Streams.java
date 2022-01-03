package com.company.streams;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Collections_vs_Streams {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList();
        names.add("a");
        names.add("b");
        names.add("c");
        for(String name: names){
            System.out.println(name);
        }

        Stream<String> streamString = names.stream();
        streamString.forEach(System.out::println);
    }
}
