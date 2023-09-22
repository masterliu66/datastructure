package com.example.leetcode.dynamic;

import org.junit.Test;

import java.util.Arrays;

/**
 * 剑指 Offer 60. n个骰子的点数
 */
public class DicesProbability {

    @Test
    public void test() {
        assert Arrays.equals(dicesProbability(1), new double[] {0.16666666666666666, 0.16666666666666666, 0.16666666666666666, 0.16666666666666666, 0.16666666666666666, 0.16666666666666666});
        assert Arrays.equals(dicesProbability(2), new double[] {0.027777777777777776, 0.05555555555555555, 0.08333333333333333, 0.1111111111111111, 0.1388888888888889, 0.16666666666666669, 0.1388888888888889, 0.1111111111111111, 0.08333333333333333, 0.05555555555555555, 0.027777777777777776});
        assert Arrays.equals(dicesProbability(3), new double[] {0.004629629629629629, 0.013888888888888888, 0.027777777777777776, 0.046296296296296294, 0.06944444444444445, 0.09722222222222222, 0.11574074074074073, 0.125, 0.125, 0.11574074074074074, 0.09722222222222224, 0.06944444444444445, 0.046296296296296294, 0.027777777777777776, 0.013888888888888888, 0.004629629629629629});
    }

    public double[] dicesProbability(int n) {

        // dp[i]表示i个骰子不同点数的概率分布
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6);

        for (int i = 2; i <= n; i++) {
            // i个骰子的点数和区间[i,6*i], 合计5*i+1种
            double[] tmp = new double[5*i+1];
            for (int j = 0; j < dp.length; j++) {
                // i个骰子的点数和概率 = 当前骰子点数概率 * 前i-1个骰子的点数和概率
                for (int k = 0; k < 6; k++) {
                    tmp[j+k] += 1.0 / 6 * dp[j];
                }
            }
            dp = tmp;
        }

        return dp;
    }

}
