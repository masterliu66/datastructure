package com.example.leetcode;

/**
 * 有一个长度为arrLen的数组，开始有一个指针在索引 0 处。
 *
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 *
 * 给你两个整数steps 和arrLen，计算并返回：在恰好执行steps次操作以后，指针仍然指向索引 0 处的方案数。
 *
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 *
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 *
 * @author CCC
 * @date 2021/5/13 23:46
 */
public class NumWays {

    public static void main(String[] args) {

        int steps = 430;
        int arrLen = 216;

        int result = new NumWays().numWays(steps, arrLen);

        System.out.println(result);
    }

    static final int MODULO = 1000000007;

    public int numWays(int steps, int arrLen) {

        // dp[i][j] 表示执行 j 次操作以后，从位置i回到位置 0 的方案数
        int[][] dp = new int[arrLen][steps + 1];

        return dfs(dp, 0, steps);
    }

    private int dfs(int[][] dp, int i, int j) {

        if (i < 0 || j < 0 || i == dp.length || j == dp[0].length) {
            return -1;
        }

        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        if (i == j) {
            dp[i][j] = 1;
            return 1;
        }

        if (i < j) {
            // 左移 dp[i][j] -> dp[i-1][j-1]
            int left = dfs(dp, i-1, j-1);
            // 右移 dp[i][j] -> dp[i+1][j-1]
            int right = dfs(dp, i+1, j-1);
            // 不动 dp[i][j] -> dp[i][j-1]
            int no = dfs(dp, i, j-1);

            if (left > 0) {
                dp[i][j] = (dp[i][j] + left) % MODULO;
            }

            if (right > 0) {
                dp[i][j] = (dp[i][j] + right) % MODULO;
            }

            if (no > 0) {
                dp[i][j] = (dp[i][j] + no) % MODULO;
            }

            return dp[i][j];
        }

        dp[i][j] = -1;

        return -1;
    }

}
