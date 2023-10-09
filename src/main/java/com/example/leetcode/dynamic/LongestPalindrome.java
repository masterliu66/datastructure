package com.example.leetcode.dynamic;

import org.junit.Test;

/**
 * 5. 最长回文子串
 */
public class LongestPalindrome {

    @Test
    public void test() {
        System.out.println(longestPalindrome("cbbd"));
    }

    public String longestPalindrome(String s) {

        int n = s.length();
        if (n <= 1) {
            return s;
        }
        // dp[i][j]表示区间[i,j]是否为回文子串
        boolean[][] dp = new boolean[n][n];
        /*
         * 初始状态: dp[i][i] = true
         * s[i] == s[j], dp[i][j] = dp[i+1][j-1]
         * s[i] != s[j], dp[i][j] = false
         */
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int start = 0;
        int end = 0;
        char[] charArray = s.toCharArray();
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (charArray[i] == charArray[j]) {
                    // 两个相同字符的情况需要特殊处理
                    dp[i][j] = len == 2 || dp[i + 1][j - 1];
                    if (dp[i][j] && (j - i) > (end - start)) {
                        start = i;
                        end = j;
                    }
                }
            }
        }

        return s.substring(start, end);
    }

}
