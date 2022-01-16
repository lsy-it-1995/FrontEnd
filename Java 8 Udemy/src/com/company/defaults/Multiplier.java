package com.company.defaults;

import java.util.List;

public interface Multiplier {

    int multiply(List<Integer> list);

    default int size(List<Integer> list){
        return list.size();
    }

    static boolean isEmpty(List<Integer> list){
        return list.size() == 0 || list == null;
    }
}
