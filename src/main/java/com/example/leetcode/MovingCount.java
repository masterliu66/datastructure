package com.example.leetcode;

import org.junit.Test;

/**
 * 剑指 Offer 13. 机器人的运动范围
 */
public class MovingCount {

    @Test
    public void test() {

        int m = 2;
        int n = 3;
        int k = 1;

        int ans = new MovingCount().movingCount(m, n, k);

        assert ans == 3;

        m = 38;
        n = 15;
        k = 9;
        ans = new MovingCount().movingCount(m, n, k);

        assert ans == 135;
    }


    public int movingCount(int m, int n, int k) {

        if (k == 0) {
            return 1;
        }

        // dp[i][j]表示行纵坐标和小于k时，是否能够到达坐标(i,j)
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for (int i = 1; i < m; i++) {
            if (digitSum(i) <= k) {
                dp[i][0] = dp[i-1][0];
            }
        }
        for (int j = 1; j < n; j++) {
            if (digitSum(j) <= k) {
                dp[0][j] = dp[0][j-1];
            }
        }
        for (int i = 1; i < m; i++) {
            if (digitSum(i) > k) {
                continue;
            }
            for (int j = 1; j < n; j++) {
                if (digitSum(i) + digitSum(j) > k) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j]) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

}
