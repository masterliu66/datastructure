package com.example.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。
 *
 * 青蛙可以跳上石子，但是不可以跳入水中。给你石子的位置列表 stones（用单元格序号 升序 表示）
 *
 * 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 *
 * 开始时，青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 *
 * 如果青蛙上一步跳跃了k个单位，那么它接下来的跳跃距离只能选择为k - 1、k或k + 1 个单位。
 *
 * 另请注意，青蛙只能向前方（终点的方向）跳跃。
 *
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 231 - 1
 * stones[0] == 0
 *
 *
 * @author CCC
 * @date 2021/4/29 23:41
 */
public class CanCross {

    @Test
    public void canCross() {

        int[] stones = {0,1,3,5,6,8,12,17};
//        int[] stones = {0,1,2,3,4,8,9,11};

        System.out.println(canCross(stones));
    }

    int[][] memo;

    public boolean canCross(int[] stones) {

        if (stones[1] != 1) {
            return false;
        }

        int n = stones.length;

//        memo = new int[n][k];

        Map<Integer, Integer> map = new HashMap<>(n);
        // 建立反向索引
        for (int i = 0; i < n; i++) {
            map.put(stones[i], i);
        }

        // dp[i][k] 表示青蛙从石头j能否跳跃k步后处于石头i, 其中stones[j] = stones[i] - k
        boolean[][] dp = new boolean[n][n + 1];
        dp[1][1] = true;
        // i表示当前位置
        for (int i = 2; i < n; i++) {

            // step表示跳跃步数
            for (int step = 1; step < i; step++) {
                if (stones[i] - step <= 0) {
                    break;
                }
                // j表示前一位置
                Integer j = map.get(stones[i] - step);
                if (j == null) {
                    continue;
                }

                // 前一位置可达
                if (dp[j][step - 1] || dp[j][step] || dp[j][step + 1]) {
                    dp[i][step] = true;
                }
            }

        }

        for (boolean cross : dp[n - 1]) {
            if (cross) {
                return true;
            }
        }

        return false;
    }

    private boolean dfs(int[] stones, int cur, int k) {

        if (memo[cur][k] != 0) {
            return memo[cur][k] == 1;
        }

        if (cur == stones.length - 1) {
            memo[cur][k] = 1;
            return true;
        }

        for (int i = cur + 1; i < stones.length; i++) {

            if (stones[i] - stones[cur] > k + 1) {
                memo[cur][k] = -1;
                return false;
            }

            int start = k > 1 ? k - 1 : 1;
            for (int step = start; step <= k + 1; step++) {

                if (stones[i] - stones[cur] == step) {
                    if (dfs(stones, i, step)) {
                        memo[cur][k] = 1;
                        return true;
                    }
                }

            }

        }

        memo[cur][k] = -1;

        return false;
    }

}
