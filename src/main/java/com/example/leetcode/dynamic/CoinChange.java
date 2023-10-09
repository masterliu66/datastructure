package com.example.leetcode.dynamic;

import org.junit.Test;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 */
public class CoinChange {

    @Test
    public void test() {
        System.out.println(coinChange(new int[] {1,2,5}, 11));
        System.out.println(coinChange(new int[] {2}, 3));
    }

    public int coinChange(int[] coins, int amount) {

        final int MAX = amount + 1;
        // dp[i]表示凑成总金额为i时最少的硬币个数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == MAX ? -1 : dp[amount];
    }
}
