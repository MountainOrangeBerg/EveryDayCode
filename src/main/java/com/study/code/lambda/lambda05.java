package com.study.code.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

public class lambda05 {

    public static void main(String[] args) {
        /**
         * 语法格式4：两个或以上
         * lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
         */
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(02);
                return Integer.compare(o1, o2);
            }
        };

        Comparator<Integer> integerComparator = (o1,o2)->{
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1,o2);
        };



        int compare = comparator.compare(12,21); System.out.println(compare);
        int lambdaResult = integerComparator.compare(12,21); System.out.println(compare);

    }

}
