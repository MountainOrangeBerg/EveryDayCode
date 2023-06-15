package com.study.code.lambda;

import java.util.function.Function;
import java.util.ArrayList;

public class MethodReferenceExample {
    public static void main(String[] args) {
        // 静态方法引用
        Function<Integer, Double> squareRoot = Math::sqrt;
        System.out.println(squareRoot.apply(16));  // 输出：4.0

        // 实例方法引用
        String str = "Hello";
        Function<Integer, Character> charAtFunction = str::charAt;
        System.out.println(charAtFunction.apply(1));  // 输出：e

        // 引用特定对象的实例方法
        ArrayList<String> list = new ArrayList<>();
        Function<String, Boolean> containsFunction = list::contains;
        System.out.println(containsFunction.apply("Hello"));  // 输出：false

        // 构造函数引用
        Function<Integer, ArrayList<Integer>> newList = ArrayList::new;
        ArrayList<Integer> numbers = newList.apply(5);
        System.out.println(numbers);  // 输出：[]
    }
}
