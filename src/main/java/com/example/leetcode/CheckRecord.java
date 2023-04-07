package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 552. 学生出勤记录 II
 *
 * @author CCC
 * @date 2021/8/18 20:52
 */
public class CheckRecord {

    public static void main(String[] args) {

        int n = 5;

        System.out.println(new CheckRecord(n).checkRecord());
    }

    public CheckRecord(int n) {
        this.n = n;
    }

    public int checkRecord() {

        return checkRecord(n);
    }

    int n;

    int MOD = 1000000007;

    int[][][] memo;

    public int checkRecord(int n) {

        /*
        * 'A'：Absent，缺勤
        * 'L'：Late，迟到
        * 'P'：Present，到场
        * */
        memo = new int[2][3][n + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return dfs(n, 0, 0);
    }

    /**
     * @param n 剩余天数
     * @param absentCount 缺席次数
     * @param lateCount 连续迟到次数
     */
    private int dfs(int n, int absentCount, int lateCount) {

        if (absentCount >= 2 || lateCount >= 3) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (memo[absentCount][lateCount][n] != -1) {
            return memo[absentCount][lateCount][n];
        }

        // 缺席
        int ans = dfs(n - 1, absentCount + 1, 0) % MOD;
        // 迟到
        ans = (ans + dfs(n - 1, absentCount, lateCount + 1)) % MOD;
        // 到场
        ans = (ans + dfs(n - 1, absentCount, 0)) % MOD;

        memo[absentCount][lateCount][n] = ans;

        return ans;
    }

}
