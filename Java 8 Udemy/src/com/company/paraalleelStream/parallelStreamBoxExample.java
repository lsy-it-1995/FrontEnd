package com.company.paraalleelStream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class parallelStreamBoxExample {
    public static void sequential(List<Integer> nums){
        long startTime = System.currentTimeMillis();
        int sum = nums.stream().reduce(0, (x, y) -> x + y);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
    public static void parallel(List<Integer> nums){
        long startTime = System.currentTimeMillis();
        int sum = nums.parallelStream().reduce(0, (x, y)-> x + y);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
    public static void main(String[] args) {
        List<Integer> nums = IntStream.rangeClosed(1, 10000)
                                .boxed()
                                .collect(Collectors.toList());
//        sequential(nums);
//        parallel(nums);
        Sum sum = new Sum();
        //sequential: 50005000
        IntStream.rangeClosed(1, 10000).forEach(sum::add);
        System.out.println(sum.getTotal());

        //parallel: everytime diff.
        IntStream.rangeClosed(1, 10000).parallel().forEach(sum::add);
        System.out.println(sum.getTotal());

    }
    static class Sum{
        private int total;
        public Sum(){
            total = 0;
        }
        public void add(int val){
            total += val;
        }
        public int getTotal(){
            return total;
        }
    }
}
