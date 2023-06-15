package com.study.code.lambda;

import java.util.Comparator;

public class lambda01 {

    public static void main(String[] args) {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        int compara = comparator.compare(1,2);
        System.out.println(compara);

    }

}
