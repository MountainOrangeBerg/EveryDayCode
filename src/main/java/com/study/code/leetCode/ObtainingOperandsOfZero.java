package com.study.code.leetCode;

/**
 * 给你两个 非负 整数 num1 和 num2 。
 *
 * 每一步 操作中，如果 num1 >= num2 ，你必须用 num1 减 num2 ；否则，你必须用 num2 减 num1 。
 *
 * 例如，num1 = 5 且 num2 = 4 ，应该用num1 减 num2 ，因此，得到 num1 = 1 和 num2 = 4 。
 * 然而，如果 num1 = 4且 num2 = 5 ，一步操作后，得到 num1 = 4 和 num2 = 1 。
 * 返回使 num1 = 0 或 num2 = 0 的 操作数 。
 *
 */
public class ObtainingOperandsOfZero {

    public static int countOperations(int num1, int num2) {
        if(num1<0||num2<0) return -1; //负一标识含有负数或有0
        int i=0;
        while (num1!=0&&num2!=0){
            ++i;
            if(num1>=num2) {
                num1 = num1-num2;
            }
            else {
                num2 = num2-num1;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        int i = countOperations(2, 3);
        System.out.println(i);
    }
}
