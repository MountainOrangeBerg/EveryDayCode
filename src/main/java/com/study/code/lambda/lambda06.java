package com.study.code.lambda;

import java.util.Comparator;

public class lambda06 {

    public static void main(String[] args) {
        /**
         * 语法格式4：两个或以上
         * 当lambda体只有一条语句时，return与大括号若有，都可以省略
         */
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        Comparator<Integer> integerComparator = (o1,o2)-> Integer.compare(o1,o2);



        int compare = comparator.compare(12,21); System.out.println(compare);
        int lambdaResult = integerComparator.compare(12,21); System.out.println(compare);

    }

}
