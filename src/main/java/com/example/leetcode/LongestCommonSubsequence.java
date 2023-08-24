package com.example.leetcode;

import org.junit.Test;

/**
 * LCR 095. 最长公共子序列
 */
public class LongestCommonSubsequence {

    @Test
    public void test() {

        String str1 = "ABCBDAB";
        String str2 = "BDCAB";

        // 求解最长公共子序列
        String lcs = longestCommonSubsequence(str1, str2);

        // 输出结果
        System.out.print("Longest Common Subsequence: ");
        System.out.println(lcs);
    }

    public String longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 回溯构造最长公共子序列
        StringBuilder builder = new StringBuilder();
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                builder.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        builder.reverse();

        return builder.toString();
    }

}
