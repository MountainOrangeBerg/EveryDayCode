package com.study.code.lambda;

import java.util.function.Consumer;

public class lambda03 {

    public static void main(String[] args) {
        /**
         * 语法格式2：一参一返回
         * 若使用集合类是前面所指定的泛型可以省略参数的类型
         */
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("这一路上走走停停");
            }
        };

        Consumer<String> stringConsumer = (s -> {
            System.out.println("留下岁月漂流的痕迹");
        });
        consumer.accept("s");
        stringConsumer.accept("s");

    }

}
