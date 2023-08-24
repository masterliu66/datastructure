package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 16. 数值的整数次方
 */
public class MyPow {

    @Test
    public void test() {

        double x = 2.00000;
        int n = -2147483648;

        assert myPow(x, n) == 0.0;
    }

    public double myPow(double x, int n) {

        long m = n;
        boolean negative = false;
        if (m < 0) {
            m = -m;
            negative = true;
        }

        double ans = 1;
        while (m > 0) {
            if ((m & 1) == 1) {
                ans *= x;
            }
            x *= x;
            m >>= 1;
        }

        return negative ? 1 / ans : ans;
    }

}
