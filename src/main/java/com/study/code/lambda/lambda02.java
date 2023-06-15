package com.study.code.lambda;

import java.util.Comparator;

public class lambda02 {

    public static void main(String[] args) {
        /**
         * 语法格式1：无参无返回值
         */
        Runnable ri = new Runnable() {
            @Override
            public void run() {
                System.out.println("old road");
            }
        };

        Runnable runnable = ()->{
            System.out.println("new road");
        };
        ri.run();
        runnable.run();

    }

}
