package com.example.leetcode.string;

import org.junit.Test;

/**
 * 43. 字符串相乘
 */
public class Multiply {

    @Test
    public void test() {

        System.out.println(multiply("9", "9"));
    }

    public String multiply(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();

        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i+j+1] += x * y;
            }
        }

        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = ansArr[0] == 0 ? 1 : 0; i < m + n; i++) {
            builder.append(ansArr[i]);
        }

        return builder.toString();
    }

}
