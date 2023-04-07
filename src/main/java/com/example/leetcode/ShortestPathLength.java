package com.example.leetcode;

import java.util.*;

/**
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
 *
 * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
 *
 * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
 *
 * n == graph.length
 * 1 <= n <= 12
 * 0 <= graph[i].length <n
 * graph[i] 不包含 i
 * 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
 * 输入的图总是连通图
 *
 * @author CCC
 * @date 2021/8/6 20:50
 */
public class ShortestPathLength {

    public static void main(String[] args) {

//        int[][] graph = {{1,2,3},{0},{0},{0}};
        int[][] graph = {{1},{0,2,4},{1,3},{2},{1,5},{4}};

        System.out.println(new ShortestPathLength(graph).shortestPathLength());
    }

    private int[][] graph;

    public ShortestPathLength(int[][] graph) {
        this.graph = graph;
    }

    public int shortestPathLength() {
        return shortestPathLength(graph);
    }

    public int shortestPathLength(int[][] graph) {

        int n = graph.length;

        // d[i][j] 表示从i到j的距离
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], 10000);
            for (int j : graph[i]) {
                d[i][j] = 1;
            }
        }

        // 使用 floyd 算法预处理出所有点对之间的最短路径长度
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        // dp[i][j] 表示到达顶点j状态为i时的最短距离
        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }

        for (int i = 1; i < 1 << n; i++) {
            if (Integer.bitCount(i) == 1) {
                int j = Integer.bitCount(Integer.lowestOneBit(i) - 1);
                dp[i][j] = 0;
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 0) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (((i >> k) & 1) == 0) {
                        continue;
                    }
                    dp[i][k] = Math.min(dp[i][k], dp[i - (1 << k)][j] + d[j][k]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[(1 << n) - 1][i]);
        }

        return ans;
    }

}
