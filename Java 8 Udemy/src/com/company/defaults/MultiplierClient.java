package com.company.defaults;

import java.util.Arrays;
import java.util.List;

public class MultiplierClient {
    public static void main(String[] args) {
        Multiplier multiplier = new MultiplierImpl();
        List<Integer> nums = Arrays.asList(1,2,3,4,5);
        System.out.println(multiplier.multiply(nums));
        System.out.println(multiplier.size(nums));
        System.out.println(Multiplier.isEmpty(nums));
    }
}
