package com.example.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 有台奇怪的打印机有以下两个特殊要求：
 *
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 *
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 *
 * @author CCC
 * @date 2021/5/24 21:31
 */
public class StrangePrinter {

    public static void main(String[] args) {
        String s = "abcabc";
        System.out.println(new StrangePrinter(s).strangePrinter());
    }

    private final String s;

    public StrangePrinter(String s) {
        this.s = s;
    }

    public int strangePrinter() {
        return strangePrinter(s);
    }

    public int strangePrinter(String s) {

        int n = s.length();

        // dp[i][j] 表示打印区间[i,j]需要的最少打印次数
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = min;
                }
            }
        }

        return dp[0][n - 1];
    }

}
