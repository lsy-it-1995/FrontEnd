package com.company.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamLimitSkipExample {
    public static Optional<Integer> limit(List<Integer> list, int shownNumber){
        return list.stream().limit(shownNumber).reduce((a,b) -> a + b);
    }
    public static Optional<Integer> skip(List<Integer> list, int skipped){
        return list.stream().skip(skipped).reduce((a,b) -> a + b);
    }
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Optional<Integer> limitOptional = limit(list, 3);
        System.out.println(limitOptional.get());
        Optional<Integer> skipOptional = skip(list, 1);
        if(skipOptional.isPresent()){
            System.out.println(skipOptional.get());
        }
    }
}
