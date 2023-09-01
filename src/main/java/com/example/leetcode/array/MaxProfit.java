package com.example.leetcode.array;

import org.junit.Test;

/**
 * 121. 买卖股票的最佳时机
 */
public class MaxProfit {

    @Test
    public void test() {

        assert 5 == maxProfit(new int[] {7,1,5,3,6,4});
        assert 0 == maxProfit(new int[] {7,6,4,3,1});
    }

    public int maxProfit(int[] prices) {

        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int min = prices[0];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return ans;
    }

}
