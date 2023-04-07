package com.example.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 *
 * @author CCC
 * @date 2021/4/28 21:31
 */
public class LongestIncreasingPath {

    @Test
    public void longestIncreasingPath() {

        int[][] matrix = {{3,4,5},{3,2,6},{2,2,1}};

        System.out.println(longestIncreasingPath(matrix));
        System.out.println(topoSort(matrix));
    }

    int[][] memo;

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int m,n;

    public int topoSort(int[][] matrix) {

        m = matrix.length;
        n = matrix[0].length;

        // outDegrees[i][j] 表示顶点(i, j)的出度
        int[][] outDegrees = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                for (int[] dir : dirs) {

                    int row = i + dir[0];
                    int col = j + dir[1];
                    if (row >= 0 && row < m && col >= 0 && col < n && matrix[row][col] > matrix[i][j]) {
                        outDegrees[i][j]++;
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        // 将所有出度为0的顶点加入队列中
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (outDegrees[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

       int ans = 0;
       while (!queue.isEmpty()) {

           int size = queue.size();

           for (int i = 0; i < size; i++) {

               // 从队列中取出一个元素, 并使其满足条件的相邻顶点出度减一
               int[] element = queue.poll();
               int row = element[0];
               int col = element[1];
               for (int[] dir : dirs) {
                   int newRow = row + dir[0];
                   int newCol = col + dir[1];
                   if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n  && matrix[newRow][newCol] < matrix[row][col]) {
                       outDegrees[newRow][newCol]--;
                       // 出度为0进队列
                       if (outDegrees[newRow][newCol] == 0) {
                           queue.offer(new int[] {newRow, newCol});
                       }
                   }
               }

           }

           ans++;
       }

        return ans;
    }

    public int longestIncreasingPath(int[][] matrix) {

        m = matrix.length;
        n = matrix[0].length;

        // memo[i][j] 表示以(i, j)为起点, 最长递增路径的长度
        memo = new int[m][n];

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(matrix, i, j));
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, int i, int j) {

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        memo[i][j] = 1;

        for (int[] dir : dirs) {

            int row = i + dir[0];
            int col = j + dir[1];

            if (row >= 0 && row < m && col >= 0 && col < n && matrix[row][col] > matrix[i][j]) {
                memo[i][j] = Math.max(memo[i][j], dfs(matrix, row, col) + 1);
            }
        }

        return memo[i][j];
    }

}
