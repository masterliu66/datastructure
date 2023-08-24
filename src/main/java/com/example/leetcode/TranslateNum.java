package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 */
public class TranslateNum {

    @Test
    public void test() {

        int num = 12258;

        int ans = translateNum(num);

        assert ans == 5;
    }

    public int translateNum(int num) {

        int maxNum = 25;

        String numStr = String.valueOf(num);
        int n = numStr.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = Integer.parseInt(String.valueOf(numStr.charAt(i)));
        }
        // dp[i]表示前i字符有效翻译组合的最大数量
        // 由于dp[i]只与dp[i-1]和dp[i-2]有关, 可以使用两个变量代替数组优化空间
        int dp0 = 1;
        int dp1 = 1;
        int res = 1;
        for (int i = 1; i < n; i++) {
            if (digits[i - 1] != 0 && digits[i - 1] * 10 + digits[i] <= maxNum) {
                if (i == 1) {
                    res = dp0 + 1;
                } else {
                    res = dp0 + dp1;
                }
            }
            dp0 = dp1;
            dp1 = res;
        }

        return res;
    }

}
