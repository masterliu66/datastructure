package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 60. n个骰子的点数
 */
public class DicesProbability {

    @Test
    public void test() {

        dicesProbability(1);
        dicesProbability(2);
        dicesProbability(3);
    }

    public double[] dicesProbability(int n) {

        int[][] dp = new int[2][6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                for (int k = 1; k <= 6; k++) {
                    dp[i % 2][j + k] += dp[(i - 1) % 2][j];
                }
            }
        }

        long sum = 0;
        for (int num : dp[(n - 1) % 2]) {
            sum += num;
        }

        System.out.println(sum);

        double[] ans = new double[5 * n + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (double) dp[(n - 1) % 2][i + n] / (double) sum;
        }

        return ans;
    }

}
