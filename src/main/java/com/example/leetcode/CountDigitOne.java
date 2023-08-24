package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 */
public class CountDigitOne {

    @Test
    public void test() {
        int ans = countDigitOne(99);
        assert ans == 20;
    }

    public int countDigitOne(int n) {
        // mulk 表示 10^k
        long mulk = 1;
        int ans = 0;
        while (n >= mulk) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }
}
