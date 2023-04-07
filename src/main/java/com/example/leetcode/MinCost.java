package com.example.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 1547. 切棍子的最小成本
 * 有一根长度为 n 个单位的木棍，棍上从 0 到 n 标记了若干位置。
 *
 * 给你一个整数数组 cuts ，其中 cuts[i] 表示你需要将棍子切开的位置。
 *
 * 你可以按顺序完成切割，也可以根据需要更改切割的顺序。
 *
 * 每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和。
 *
 * 对棍子进行切割将会把一根木棍分成两根较小的木棍（这两根木棍的长度和就是切割前木棍的长度）。
 *
 * 返回切棍子的 最小总成本 。
 *
 * 2 <= n <= 10^6
 * 1 <= cuts.length <= min(n - 1, 100)
 * 1 <= cuts[i] <= n - 1
 * cuts 数组中的所有整数都 互不相同
 *
 * @author CCC
 * @date 2021/5/17 22:57
 */
public class MinCost {

    public static void main(String[] args) {

        int n = 7;
        int[] cuts = {1,3,4,5};

        int res = new MinCost(n, cuts).minCost();

        System.out.println(res);
    }

    private final int n;

    private final int[] cuts;

    public MinCost(int n, int[] cuts) {
        this.n = n;
        this.cuts = cuts;
    }

    public int minCost() {

        Arrays.sort(cuts);

        int m = cuts.length;

        int[] newCuts = new int[m + 2];
        newCuts[0] = 0;
        System.arraycopy(cuts, 0, newCuts, 1, m);
        newCuts[m + 1] = n;

        // dp[i][j] 表示newCuts[i - 1, j + 1]区间将木棍全部切开的最小成本
        int[][] dp = new int[m + 2][m + 2];
        for (int i = m; i >= 1; i--) {
            for (int j = i; j <= m; j++) {
                int min = i == j ? 0 : Integer.MAX_VALUE;
                // 枚举左端点为newCuts[i-1],右端点为newCuts[j+1]时第一刀的位置
                for (int k = i; k <= j; k++) {
                    // 剩余可切开的位置为(newCuts[i],newCuts[k-1]) 和 (newCuts[k+1],newCuts[j])
                    min = Math.min(min, dp[i][k - 1] + dp[k + 1][j]);
                }
                dp[i][j] = min + (newCuts[j + 1] - newCuts[i - 1]);
            }
        }

        return dp[1][m];
    }

}
