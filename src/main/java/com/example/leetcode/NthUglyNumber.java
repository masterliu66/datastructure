package com.example.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 剑指 Offer 49. 丑数
 */
public class NthUglyNumber {

    @Test
    public void test() {
        
        assert nthUglyNumber(10) == 12;
        assert nthUglyNumber(106) == 1920;
        assert nthUglyNumber(167) == 8192;
        assert nthUglyNumber(1352) == 402653184;

        assert dp(10) == 12;
        assert dp(106) == 1920;
        assert dp(167) == 8192;
        assert dp(1352) == 402653184;
    }

    public int nthUglyNumber(int n) {

        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<>();
        seen.add(1L);
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.add(1L);
        for (int i = 0; i < n - 1;i ++) {
            long num = heap.poll();
            for (int factor : factors) {
                long newNum = num * factor;
                if (seen.add(newNum)) {
                    heap.offer(newNum);
                }
            }
        }

        return (int) heap.peek().longValue();
    }

    public int dp(int n) {
        // dp[i]表示第i+1个丑数
        int[] dp = new int[n];
        dp[0] = 1;
        // 定义三个指针, 分别表示以2,3,5为倍数递增, 满足dp[i]>dp[i-1]的最小下标
        int pa = 0;
        int pb = 0;
        int pc = 0;
        for (int i = 1; i < n; i++) {
            int a = 2 * dp[pa];
            int b = 3 * dp[pb];
            int c = 5 * dp[pc];
            int min = Math.min(Math.min(a, b), c);
            if (a == min) {
                dp[i] = a;
                pa++;
            }
            if (b == min) {
                dp[i] = b;
                pb++;
            }
            if (c == min) {
                dp[i] = c;
                pc++;
            }
        }

        return dp[n-1];
    }

}
