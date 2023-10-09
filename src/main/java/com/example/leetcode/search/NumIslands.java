package com.example.leetcode.search;

import org.junit.Test;

/**
 * 200. 岛屿数量
 */
public class NumIslands {

    @Test
    public void test() {
        char[][] grid = {
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '1'}
        };
        assert numIslands(grid) == 3;
    }

    private static final int[] directions = {0, 1, 0, -1, 0};

    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    dfs(grid, i, j);
                }
            }
        }

        return cnt;
    }

    private void dfs(char[][] grid, int i, int j) {

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0';

        for (int idx = 0; idx < directions.length - 1; idx++) {
            dfs(grid, i + directions[idx], j + directions[idx + 1]);
        }
    }

}
