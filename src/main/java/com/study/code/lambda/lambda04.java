package com.study.code.lambda;

import java.util.function.Consumer;

public class lambda04 {

    public static void main(String[] args) {
        /**
         * 语法格式4：一参一返回
         * lambda若只需要一个参数时，参数的小括号可以省略
         */
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("这一路上走走停停");
            }
        };

        Consumer<String> stringConsumer = s -> {
            System.out.println("留下岁月漂流的痕迹");
        };
        consumer.accept("s");
        stringConsumer.accept("s");

    }

}
