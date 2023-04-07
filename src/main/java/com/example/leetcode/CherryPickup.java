package com.example.leetcode;

import java.util.Arrays;

/**
 * 一个N x N的网格(grid)代表了一块樱桃地，每个格子由以下三种数字的一种来表示：
 * 0 表示这个格子是空的，所以你可以穿过它。
 * 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
 * -1 表示这个格子里有荆棘，挡着你的路。
 * 你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃：
 *
 * 从位置(0, 0) 出发，最后到达 (N-1, N-1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1
 * 的格子）；当到达 (N-1, N-1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
 * 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）；
 * 如果在 (0, 0) 和 (N-1, N-1) 之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。
 *
 * grid 是一个N * N 的二维数组，N的取值范围是1 <= N <= 50。
 * 每一个grid[i][j] 都是集合{-1, 0, 1}其中的一个数。
 * 可以保证起点grid[0][0]和终点grid[N-1][N-1]的值都不会是 -1。
 *
 * @author CCC
 * @date 2021/3/14 22:38
 */
public class CherryPickup {

    int n;

    int[][] grid;

    int[][][] memo;

    public static void main(String[] args) {

        int[][] grid = {{1, 1, -1},
                        {1, -1, 1},
                        {-1, 1, 1}};

        /*int[][] grid = {{1, 1, 1, 0, 0},
                        {0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 1, 1}};*/

        /*int[][] grid = {{1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1}};*/

        CherryPickup cherryPickup = new CherryPickup();

        int pickup = cherryPickup.cherryPickup(grid);

        System.out.println(pickup);
    }

    public int cherryPickup(int[][] grid) {

        this.grid = grid;

        this.n = grid.length;

        // 用(r1, c1), (r2, c2)表示走了t步后的两个人的位置, 其中r2 = r1 + c1 - c2
        // 记memo[r1][c1][c2]为摘得樱桃的最大值
        this.memo = new int[n][n][n];
        for (int[][] layer : this.memo) {
            for (int[] row : layer) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }

        return Math.max(0, dp(0, 0, 0));
    }

    private int dp(int r1, int c1, int c2) {

        int r2 = r1 + c1 - c2;

        // 边界条件
        if (r1 == n || c1 == n || r2 == n ||  c2 == n || grid[r1][c1] < 0 || grid[r2][c2] < 0) {
            return -10000;
        }

        // A, B两人都达到终点
        if (r1 == n - 1 && c1 == n - 1) {
            return grid[r1][c1];
        }

        // 已动态规划过, 直接返回结果
        if (memo[r1][c1][c2] != Integer.MIN_VALUE) {
            return memo[r1][c1][c2];
        }

        int ans = grid[r1][c1];
        // (r1, c1) == (r2, c2) 的情况下, 不再重复计数
        if (c1 != c2) {
            ans += grid[r2][c2];
        }
        /*
         * A (r1, c1), B (r2, c2)
         * A向下、B向下 dp[r1 + 1][c1][c2]
         * A向右、B向下 dp[r1][c1 + 1][c2]
         * A向下、B向右 dp[r1 + 1][c1][c2 + 1]
         * A向右、B向右 dp[r1][c1 + 1][c2 + 1]
         * */
        ans += max(dp(r1 + 1, c1, c2), dp(r1, c1 + 1, c2),
                dp(r1 + 1, c1, c2 + 1), dp(r1, c1 + 1, c2 + 1));
        memo[r1][c1][c2] = ans;

        return ans;
    }

    private int max(int... values) {

        int max = Integer.MIN_VALUE;

        for (int value : values) {
            max = Math.max(max, value);
        }

        return max;
    }

    /** 贪心算法, 无法得出最优解 */
    public int greedyCherryPickup(int[][] grid) {
        int ans = 0;
        int[][] path = bestPath(grid);
        if (path == null) {
            return 0;
        }
        for (int[] step : path) {
            ans += grid[step[0]][step[1]];
            grid[step[0]][step[1]] = 0;
        }

        int[][] backBestPath = bestPath(grid);

        for (int[] step : backBestPath) {
            ans += grid[step[0]][step[1]];
        }

        return ans;
    }

    public int[][] bestPath(int[][] grid) {
        int n = grid.length;
        // 从任何位置 (i, j) 到右下角最多能摘到的樱桃数
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        dp[n - 1][n - 1] = grid[n - 1][n - 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] < 0) {
                    continue;
                }
                if (i == n - 1 && j == n - 1) {
                    continue;
                }
                dp[i][j] = Math.max(i + 1 < n ? dp[i + 1][j] : Integer.MIN_VALUE,
                        j + 1 < n ? dp[i][j + 1] : Integer.MIN_VALUE);
                dp[i][j] += grid[i][j];
            }
        }
        if (dp[0][0] < 0) {
            return null;
        }
        // 找到从左上角到右下角可以摘到最多樱桃的路径，通过 dp 来判断移动的方向
        // 基于比较 dp[i+1][j] 和 dp[i][j+1]
        int[][] ans = new int[2 * n - 1][2];
        int i = 0, j = 0, t = 0;
        while (i != n - 1 || j != n - 1) {
            if (j + 1 == n || (i + 1 < n && dp[i + 1][j] >= dp[i][j + 1])) {
                i++;
            } else {
                j++;
            }

            ans[t][0] = i;
            ans[t][1] = j;
            t++;
        }
        return ans;
    }

}
