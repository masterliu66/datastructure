package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 14- I. 剪绳子
 * 剑指 Offer 14- II. 剪绳子 II
 */
public class CuttingRope {

    @Test
    public void test() {

        int n = 10;

        int ans = cuttingRope(n);

        assert ans == 36;
    }

    public int cuttingRope(int n) {

        // dp[i]表示长度为i时每段绳子的最大乘积
        int[] dp = new int[n+1];
        for (int i = 2; i <=n; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                // 最少需要切一次, dp[i-j]的值可能小于绳子本身长度
                dp[i] = Math.max(dp[i], j * Math.max(dp[i-j], i-j));
            }
        }

        return dp[n];
    }

    public int cuttingRope2(int n) {

        if (n <= 3) {
            return n - 1;
        }

        if (n == 4) {
            return 4;
        }

        int mod = 1000000007;

        long res = 1;
        while (n > 4) {
            n -= 3;
            res = res * 3 % mod;
        }
        res = res * n % mod;

        return (int) res;
    }

}
